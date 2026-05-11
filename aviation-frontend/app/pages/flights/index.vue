<script setup>
import { ref, reactive, computed } from 'vue'

const { data: flights, refresh: refreshFlights } = useFetch('http://localhost:8080/flight-service/api/flights', { lazy: true })
const { data: crews } = useFetch('http://localhost:8080/crew-service/api/crews', { lazy: true })
const { data: aircrafts } = useFetch('http://localhost:8080/aircraft-service/api/aircrafts', { lazy: true })

const toast = useToast()

const columns = [
  { accessorKey: 'id', header: 'ID' },
  { accessorKey: 'flightNumber', header: 'Flight No' },
  { accessorKey: 'origin', header: 'Origin' },
  { accessorKey: 'destination', header: 'Destination' },
  { accessorKey: 'aircraftId', header: 'Aircraft ID' },
  { accessorKey: 'pilotId', header: 'Pilot ID' }
]

const isModalOpen = ref(false)
const formState = reactive({
  flightNumber: '',
  origin: '',
  destination: '',
  aircraftId: '',
  pilotId: ''
})

const aircraftOptions = computed(() => {
  return aircrafts.value?.map(a => ({ label: `${a.model} (ID: ${a.id})`, value: a.id })) || []
})

const pilotOptions = computed(() => {
  return crews.value?.filter(c => c.role.includes('Pilot')).map(c => ({ label: c.name, value: c.id })) || []
})

function openAddModal() {
  formState.flightNumber = ''
  formState.origin = ''
  formState.destination = ''
  formState.aircraftId = ''
  formState.pilotId = ''
  isModalOpen.value = true
}

async function saveFlight() {
  await $fetch('http://localhost:8080/flight-service/api/flights', {
    method: 'POST',
    body: formState
  })
  isModalOpen.value = false
  await refreshFlights()
  
  toast.add({
    title: 'Flight Scheduled',
    description: `Flight ${formState.flightNumber} has been successfully registered.`,
    color: 'success',
    icon: 'i-heroicons-check-circle'
  })
}
</script>

<template>
  <div>
    <div class="flex justify-between items-center mb-6">
      <div>
        <h2 class="text-2xl font-bold text-gray-900 dark:text-white">
          Flight Operations
        </h2>
        <p class="text-gray-500 text-sm">
          Monitor and manage global flight schedules and orchestrations.
        </p>
      </div>
      <UButton
        icon="i-heroicons-plus"
        label="Schedule New Flight"
        color="primary"
        @click="openAddModal"
      />
    </div>

    <UCard shadow="sm">
      <UTable
        :columns="columns"
        :data="flights"
      >
      </UTable>
    </UCard>

    <UModal v-model="isModalOpen" :ui="{ width: 'sm:max-w-xl' }" prevent-close>
      <UCard :ui="{ ring: '', divide: 'divide-y divide-gray-100 dark:divide-gray-800' }">
        <template #header>
          <div class="flex items-center justify-between">
            <h3 class="text-lg font-bold leading-6 text-gray-900 dark:text-white flex items-center gap-2">
              <div class="p-1.5 rounded-md bg-sky-100 dark:bg-sky-900/50">
                <UIcon name="i-heroicons-paper-airplane" class="w-5 h-5 text-sky-600 dark:text-sky-400" />
              </div>
              Schedule Flight Operation
            </h3>
            <UButton color="neutral" variant="ghost" icon="i-heroicons-x-mark-20-solid" class="-my-1" @click="isModalOpen = false" />
          </div>
        </template>

        <div class="px-2 pb-2">
          <p class="text-sm text-gray-500 mb-6">
            Complete the form below to register a new flight. Ensure you assign an active aircraft and an available captain to finalize the dispatch.
          </p>

          <form class="space-y-6" @submit.prevent="saveFlight">
            <!-- Section 1: Route Details -->
            <div class="bg-gray-50 dark:bg-gray-900/30 p-4 rounded-xl border border-gray-100 dark:border-gray-800 space-y-4">
              <h4 class="text-sm font-semibold text-gray-700 dark:text-gray-300 flex items-center gap-2">
                <UIcon name="i-heroicons-map" class="w-4 h-4 text-sky-500" />
                Flight Route Details
              </h4>
              
              <UFormGroup label="Flight Number (Callsign)">
                <UInput v-model="formState.flightNumber" placeholder="e.g. TK1903" required />
              </UFormGroup>
              
              <div class="grid grid-cols-2 gap-4">
                <UFormGroup label="Origin (Departure)">
                  <UInput v-model="formState.origin" placeholder="e.g. Istanbul (IST)" required />
                </UFormGroup>
                <UFormGroup label="Destination (Arrival)">
                  <UInput v-model="formState.destination" placeholder="e.g. London (LHR)" required />
                </UFormGroup>
              </div>
            </div>

            <!-- Section 2: Asset Assignment -->
            <div class="bg-gray-50 dark:bg-gray-900/30 p-4 rounded-xl border border-gray-100 dark:border-gray-800 space-y-4">
              <h4 class="text-sm font-semibold text-gray-700 dark:text-gray-300 flex items-center gap-2">
                <UIcon name="i-heroicons-wrench-screwdriver" class="w-4 h-4 text-amber-500" />
                Asset & Crew Assignment
              </h4>

              <UFormGroup label="Assign Aircraft">
                <USelect v-model="formState.aircraftId" :items="aircraftOptions" required placeholder="Choose a registered Aircraft" />
              </UFormGroup>
              
              <UFormGroup label="Assign Captain / Pilot">
                <USelect v-model="formState.pilotId" :items="pilotOptions" required placeholder="Choose an available Pilot" />
              </UFormGroup>
            </div>

            <div class="flex justify-end gap-3 pt-2">
              <UButton label="Cancel" color="neutral" variant="ghost" @click="isModalOpen = false" />
              <UButton type="submit" label="Authorize Flight" color="primary" />
            </div>
          </form>
        </div>
      </UCard>
    </UModal>
  </div>
</template>
