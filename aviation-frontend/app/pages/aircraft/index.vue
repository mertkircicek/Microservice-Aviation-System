<script setup>
import { ref, reactive } from 'vue'

const { data: aircrafts, refresh } = useFetch('http://localhost:8080/aircraft-service/api/aircrafts', { lazy: true })
const toast = useToast()

const columns = [
  { accessorKey: 'id', header: 'ID' },
  { accessorKey: 'model', header: 'Model' },
  { accessorKey: 'status', header: 'Status' }
]

const isModalOpen = ref(false)
const formState = reactive({
  model: '',
  status: 'ACTIVE'
})

const statusOptions = ['ACTIVE', 'MAINTENANCE', 'DECOMMISSIONED']

function openAddModal() {
  formState.model = ''
  formState.status = 'ACTIVE'
  isModalOpen.value = true
}

async function saveAircraft() {
  await $fetch('http://localhost:8080/aircraft-service/api/aircrafts', {
    method: 'POST',
    body: formState
  })
  isModalOpen.value = false
  await refresh()
  
  toast.add({
    title: 'Aircraft Registered',
    description: `${formState.model} has been added to the fleet.`,
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
          Aircraft Inventory
        </h2>
        <p class="text-gray-500 text-sm">
          Monitor and manage your fleet and aircraft statuses.
        </p>
      </div>
      <UButton
        icon="i-heroicons-plus"
        label="Add New Aircraft"
        color="primary"
        @click="openAddModal"
      />
    </div>

    <UCard shadow="sm">
      <UTable
        :columns="columns"
        :data="aircrafts"
      >
        <template #status-cell="{ row }">
          <UBadge
            :color="row.original.status === 'ACTIVE' ? 'success' : row.original.status === 'MAINTENANCE' ? 'warning' : 'red'"
            variant="subtle"
          >
            {{ row.original.status }}
          </UBadge>
        </template>
      </UTable>
    </UCard>

    <UModal v-model="isModalOpen" prevent-close>
      <UCard :ui="{ ring: '', divide: 'divide-y divide-gray-100 dark:divide-gray-800' }">
        <template #header>
          <div class="flex items-center justify-between">
            <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
              Add New Aircraft
            </h3>
            <UButton color="neutral" variant="ghost" icon="i-heroicons-x-mark-20-solid" class="-my-1" @click="isModalOpen = false" />
          </div>
        </template>

        <form class="space-y-4" @submit.prevent="saveAircraft">
          <UFormField label="Aircraft Model">
            <UInput v-model="formState.model" placeholder="e.g. Boeing 737" required />
          </UFormField>
          <UFormField label="Status">
            <USelect v-model="formState.status" :items="statusOptions" />
          </UFormField>
          <div class="flex justify-end gap-3 mt-6">
            <UButton label="Cancel" color="neutral" variant="ghost" @click="isModalOpen = false" />
            <UButton type="submit" label="Save Aircraft" color="primary" />
          </div>
        </form>
      </UCard>
    </UModal>
  </div>
</template>
