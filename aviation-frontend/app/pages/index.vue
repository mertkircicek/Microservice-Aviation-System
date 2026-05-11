<template>
  <div class="space-y-8 pb-12">
    <!-- Hero Section -->
    <div class="relative overflow-hidden rounded-3xl bg-gradient-to-br from-sky-500 to-indigo-600 p-8 sm:p-10 text-white shadow-xl">
      <div class="absolute top-0 right-0 -mt-10 -mr-10 w-64 h-64 bg-white opacity-10 rounded-full blur-3xl" />
      <div class="absolute bottom-0 right-20 w-40 h-40 bg-sky-300 opacity-20 rounded-full blur-2xl" />

      <div class="relative z-10">
        <h2 class="text-3xl sm:text-4xl font-extrabold mb-4 tracking-tight">
          Welcome to AviationOS
        </h2>
        <p class="text-sky-100 max-w-xl text-lg mb-8 leading-relaxed">
          Monitor your fleet, manage crew schedules, and track global flights in real-time. All systems are operating normally.
        </p>
        <div class="flex gap-4 flex-wrap">
          <UButton
            color="neutral"
            variant="solid"
            size="lg"
            class="font-semibold px-6 shadow-md hover:shadow-lg transition-all"
            to="/flights"
          >
            View Live Flights
          </UButton>
          <UButton
            color="neutral"
            variant="ghost"
            size="lg"
            class="font-semibold px-6 hover:bg-white/10"
            to="/aircraft"
          >
            Check Fleet Status
          </UButton>
        </div>
      </div>
    </div>

    <!-- Stat Cards -->
    <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
      <UCard class="glass-card card-hover border-t-4 border-t-sky-500 overflow-hidden relative">
        <div class="absolute top-0 right-0 p-4 opacity-10">
          <UIcon
            name="i-heroicons-users"
            class="w-24 h-24 text-sky-500"
          />
        </div>
        <div class="flex items-center gap-5 relative z-10">
          <div class="p-4 bg-sky-100 dark:bg-sky-500/20 rounded-2xl">
            <UIcon
              name="i-heroicons-users"
              class="w-8 h-8 text-sky-500"
            />
          </div>
          <div>
            <div class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-1">
              Total Personnel
            </div>
            <div class="text-3xl font-extrabold text-gray-900 dark:text-white">
              12
            </div>
          </div>
        </div>
      </UCard>

      <UCard class="glass-card card-hover border-t-4 border-t-emerald-500 overflow-hidden relative">
        <div class="absolute top-0 right-0 p-4 opacity-10">
          <UIcon
            name="i-heroicons-rocket-launch"
            class="w-24 h-24 text-emerald-500"
          />
        </div>
        <div class="flex items-center gap-5 relative z-10">
          <div class="p-4 bg-emerald-100 dark:bg-emerald-500/20 rounded-2xl">
            <UIcon
              name="i-heroicons-rocket-launch"
              class="w-8 h-8 text-emerald-500"
            />
          </div>
          <div>
            <div class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-1">
              Active Aircrafts
            </div>
            <div class="text-3xl font-extrabold text-gray-900 dark:text-white">
              8
            </div>
          </div>
        </div>
      </UCard>

      <UCard class="glass-card card-hover border-t-4 border-t-amber-500 overflow-hidden relative">
        <div class="absolute top-0 right-0 p-4 opacity-10">
          <UIcon
            name="i-heroicons-calendar-days"
            class="w-24 h-24 text-amber-500"
          />
        </div>
        <div class="flex items-center gap-5 relative z-10">
          <div class="p-4 bg-amber-100 dark:bg-amber-500/20 rounded-2xl">
            <UIcon
              name="i-heroicons-calendar-days"
              class="w-8 h-8 text-amber-500"
            />
          </div>
          <div>
            <div class="text-sm font-medium text-gray-500 dark:text-gray-400 mb-1">
              Upcoming Flights
            </div>
            <div class="text-3xl font-extrabold text-gray-900 dark:text-white">
              3
            </div>
          </div>
        </div>
      </UCard>
    </div>

    <!-- Recent Activity & Chart placeholder -->
    <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
      <div class="lg:col-span-2">
        <UCard class="glass-card h-full">
          <template #header>
            <div class="flex items-center justify-between">
              <h3 class="text-lg font-bold text-gray-900 dark:text-white flex items-center gap-2">
                <UIcon
                  name="i-heroicons-chart-bar"
                  class="text-sky-500"
                />
                Fleet Utilization
              </h3>
              <UButton
                variant="ghost"
                color="neutral"
                icon="i-heroicons-ellipsis-horizontal"
              />
            </div>
          </template>

          <div class="h-64 flex flex-col items-center justify-center text-center p-6 border-2 border-dashed border-gray-200 dark:border-gray-800 rounded-xl bg-gray-50/50 dark:bg-gray-900/50">
            <UIcon
              name="i-heroicons-presentation-chart-line"
              class="w-16 h-16 text-gray-300 dark:text-gray-700 mb-4"
            />
            <p class="text-gray-500 dark:text-gray-400 font-medium">
              Chart visualization will appear here.
            </p>
            <p class="text-sm text-gray-400 dark:text-gray-500 mt-2">
              Connect to the telemetry service to view real-time data.
            </p>
          </div>
        </UCard>
      </div>

      <div>
        <UCard class="glass-card h-full">
          <template #header>
            <h3 class="text-lg font-bold text-gray-900 dark:text-white flex items-center gap-2">
              <UIcon
                name="i-heroicons-bolt"
                class="text-amber-500"
              />
              Recent Activity
            </h3>
          </template>

          <div class="space-y-6">
            <div class="flex gap-4">
              <div class="mt-1 bg-emerald-100 dark:bg-emerald-900/30 p-2 rounded-full h-fit">
                <UIcon
                  name="i-heroicons-check-circle"
                  class="w-5 h-5 text-emerald-600 dark:text-emerald-400"
                />
              </div>
              <div>
                <p class="text-sm font-semibold text-gray-900 dark:text-white">
                  Flight TK1992 Landed
                </p>
                <p class="text-xs text-gray-500 mt-1">
                  Airbus A320 successfully landed at IST.
                </p>
                <p class="text-xs text-gray-400 mt-2">
                  10 mins ago
                </p>
              </div>
            </div>

            <div class="flex gap-4">
              <div class="mt-1 bg-sky-100 dark:bg-sky-900/30 p-2 rounded-full h-fit">
                <UIcon
                  name="i-heroicons-arrow-path"
                  class="w-5 h-5 text-sky-600 dark:text-sky-400"
                />
              </div>
              <div>
                <p class="text-sm font-semibold text-gray-900 dark:text-white">
                  Maintenance Scheduled
                </p>
                <p class="text-xs text-gray-500 mt-1">
                  Boeing 737 added to the maintenance queue.
                </p>
                <p class="text-xs text-gray-400 mt-2">
                  2 hours ago
                </p>
              </div>
            </div>

            <div class="flex gap-4">
              <div class="mt-1 bg-indigo-100 dark:bg-indigo-900/30 p-2 rounded-full h-fit">
                <UIcon
                  name="i-heroicons-user-plus"
                  class="w-5 h-5 text-indigo-600 dark:text-indigo-400"
                />
              </div>
              <div>
                <p class="text-sm font-semibold text-gray-900 dark:text-white">
                  New Pilot Onboarded
                </p>
                <p class="text-xs text-gray-500 mt-1">
                  Capt. Yılmaz joined the active roster.
                </p>
                <p class="text-xs text-gray-400 mt-2">
                  Yesterday
                </p>
              </div>
            </div>
          </div>

          <template #footer>
            <UButton
              variant="ghost"
              color="neutral"
              block
              class="text-sm font-medium"
            >
              View All Activity
            </UButton>
          </template>
        </UCard>
      </div>
    </div>
  </div>
</template>
