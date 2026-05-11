<script setup>
import { computed } from 'vue'

// Data Fetching
const { data: crews } = useFetch('http://localhost:8080/crew-service/api/crews', { lazy: true })
const { data: aircrafts } = useFetch('http://localhost:8080/aircraft-service/api/aircrafts', { lazy: true })
const { data: flights } = useFetch('http://localhost:8080/flight-service/api/flights', { lazy: true })

// Derived Stats
const totalPersonnel = computed(() => crews.value?.length || 0)
const activeAircrafts = computed(() => aircrafts.value?.length || 0)
const upcomingFlights = computed(() => flights.value?.length || 0)

</script>

<template>
  <div class="space-y-8 pb-12">
    <!-- Hero Section -->
    <div class="relative overflow-hidden rounded-3xl bg-gradient-to-br from-sky-500 to-indigo-600 p-8 sm:p-10 text-white shadow-xl">
      <div class="absolute top-0 right-0 -mt-10 -mr-10 w-64 h-64 bg-white opacity-10 rounded-full blur-3xl" />
      <div class="absolute bottom-0 right-20 w-40 h-40 bg-sky-300 opacity-20 rounded-full blur-2xl" />

      <div class="relative z-10 flex flex-col md:flex-row items-center justify-between">
        <div>
          <h2 class="text-3xl sm:text-4xl font-extrabold mb-4 tracking-tight">
            Welcome to AviationOS
          </h2>
          <p class="text-sky-100 max-w-xl text-lg leading-relaxed">
            Monitor your fleet, manage crew schedules, and orchestrate global flight operations. System is fully synchronized with live services.
          </p>
        </div>
        <UIcon name="i-heroicons-globe-europe-africa" class="w-32 h-32 opacity-20 hidden md:block" />
      </div>
    </div>

    <!-- Stat Cards -->
    <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
      <UCard class="glass-card card-hover border-t-4 border-t-sky-500 overflow-hidden relative">
        <div class="absolute top-0 right-0 p-4 opacity-10">
          <UIcon name="i-heroicons-users" class="w-24 h-24 text-sky-500" />
        </div>
        <div class="flex items-center gap-5 relative z-10">
          <div class="p-4 bg-sky-100 dark:bg-sky-500/20 rounded-2xl">
            <UIcon name="i-heroicons-users" class="w-8 h-8 text-sky-500" />
          </div>
          <div>
            <div class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-1">
              Active Personnel
            </div>
            <div class="text-3xl font-extrabold text-gray-900 dark:text-white">
              {{ totalPersonnel }}
            </div>
          </div>
        </div>
      </UCard>

      <UCard class="glass-card card-hover border-t-4 border-t-emerald-500 overflow-hidden relative">
        <div class="absolute top-0 right-0 p-4 opacity-10">
          <UIcon name="i-heroicons-rocket-launch" class="w-24 h-24 text-emerald-500" />
        </div>
        <div class="flex items-center gap-5 relative z-10">
          <div class="p-4 bg-emerald-100 dark:bg-emerald-500/20 rounded-2xl">
            <UIcon name="i-heroicons-rocket-launch" class="w-8 h-8 text-emerald-500" />
          </div>
          <div>
            <div class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-1">
              Fleet Size
            </div>
            <div class="text-3xl font-extrabold text-gray-900 dark:text-white">
              {{ activeAircrafts }}
            </div>
          </div>
        </div>
      </UCard>

      <UCard class="glass-card card-hover border-t-4 border-t-amber-500 overflow-hidden relative">
        <div class="absolute top-0 right-0 p-4 opacity-10">
          <UIcon name="i-heroicons-calendar-days" class="w-24 h-24 text-amber-500" />
        </div>
        <div class="flex items-center gap-5 relative z-10">
          <div class="p-4 bg-amber-100 dark:bg-amber-500/20 rounded-2xl">
            <UIcon name="i-heroicons-calendar-days" class="w-8 h-8 text-amber-500" />
          </div>
          <div>
            <div class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-1">
              Scheduled Flights
            </div>
            <div class="text-3xl font-extrabold text-gray-900 dark:text-white">
              {{ upcomingFlights }}
            </div>
          </div>
        </div>
      </UCard>
    </div>

    <!-- Recent Activity -->
    <div class="grid grid-cols-1 gap-8">
      <div>
        <UCard class="glass-card h-full">
          <template #header>
            <h3 class="text-lg font-bold text-gray-900 dark:text-white flex items-center gap-2">
              <UIcon name="i-heroicons-bolt" class="text-amber-500" />
              Latest Flight Logs
            </h3>
          </template>

          <div class="space-y-6">
            <div v-if="flights && flights.length > 0" class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div v-for="flight in flights.slice(-4).reverse()" :key="flight.id" class="flex gap-4 p-4 rounded-xl border border-gray-100 dark:border-gray-800 bg-gray-50/50 dark:bg-gray-900/50 transition-colors hover:bg-sky-50 dark:hover:bg-sky-900/20">
                <div class="mt-1 bg-sky-100 dark:bg-sky-900/50 p-3 rounded-full h-fit shadow-sm">
                  <UIcon name="i-heroicons-paper-airplane" class="w-6 h-6 text-sky-600 dark:text-sky-400" />
                </div>
                <div>
                  <p class="text-md font-semibold text-gray-900 dark:text-white flex items-center gap-2">
                    Flight {{ flight.flightNumber }} Authorized
                    <UBadge color="emerald" variant="subtle" size="xs">Live</UBadge>
                  </p>
                  <p class="text-sm text-gray-500 mt-1">
                    Route planned from <span class="font-medium text-gray-700 dark:text-gray-300">{{ flight.origin }}</span> to <span class="font-medium text-gray-700 dark:text-gray-300">{{ flight.destination }}</span>.
                  </p>
                </div>
              </div>
            </div>
            <div v-else class="text-center py-12 text-gray-500 bg-gray-50 dark:bg-gray-900/30 rounded-xl border border-dashed border-gray-200 dark:border-gray-800">
              <UIcon name="i-heroicons-document-magnifying-glass" class="w-12 h-12 mx-auto mb-3 text-gray-300 dark:text-gray-600" />
              No flight activity logs found in the database. Head over to Flights to schedule one!
            </div>
          </div>
        </UCard>
      </div>
    </div>
  </div>
</template>
