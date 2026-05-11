<template>
  <div class="flex h-screen bg-gray-50 dark:bg-gray-950 font-sans selection:bg-sky-500/30">
    <!-- Sidebar -->
    <aside
      class="w-72 glass border-r border-white/20 dark:border-gray-800 flex flex-col transition-all duration-300 z-20 absolute md:relative h-full"
      :class="[isSidebarOpen ? 'left-0' : '-left-72 md:left-0']"
    >
      <div class="p-6 flex items-center justify-between">
        <h1 class="text-2xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-sky-400 to-indigo-500 flex items-center gap-3 tracking-tight">
          <UIcon
            name="i-heroicons-paper-airplane"
            class="text-sky-500 w-8 h-8"
          />
          AviationOS
        </h1>
        <UButton
          icon="i-heroicons-x-mark"
          variant="ghost"
          color="neutral"
          class="md:hidden"
          @click="isSidebarOpen = false"
        />
      </div>

      <div class="px-4 pb-4">
        <div class="text-xs font-semibold text-gray-400 uppercase tracking-widest px-2 mb-2 mt-4">
          Navigation
        </div>
        <nav class="space-y-2">
          <UButton
            to="/"
            variant="ghost"
            color="neutral"
            size="lg"
            block
            class="justify-start font-medium transition-all duration-200 hover:bg-sky-50 dark:hover:bg-sky-900/20 hover:text-sky-600 dark:hover:text-sky-400 rounded-xl"
          >
            <template #leading>
              <UIcon
                name="i-heroicons-home"
                class="w-5 h-5"
              />
            </template>
            Dashboard
          </UButton>
          <UButton
            to="/crew"
            variant="ghost"
            color="neutral"
            size="lg"
            block
            class="justify-start font-medium transition-all duration-200 hover:bg-sky-50 dark:hover:bg-sky-900/20 hover:text-sky-600 dark:hover:text-sky-400 rounded-xl"
          >
            <template #leading>
              <UIcon
                name="i-heroicons-users"
                class="w-5 h-5"
              />
            </template>
            Crew Management
          </UButton>
          <UButton
            to="/aircraft"
            variant="ghost"
            color="neutral"
            size="lg"
            block
            class="justify-start font-medium transition-all duration-200 hover:bg-sky-50 dark:hover:bg-sky-900/20 hover:text-sky-600 dark:hover:text-sky-400 rounded-xl"
          >
            <template #leading>
              <UIcon
                name="i-heroicons-rocket-launch"
                class="w-5 h-5"
              />
            </template>
            Aircraft Inventory
          </UButton>
          <UButton
            to="/flights"
            variant="ghost"
            color="neutral"
            size="lg"
            block
            class="justify-start font-medium transition-all duration-200 hover:bg-sky-50 dark:hover:bg-sky-900/20 hover:text-sky-600 dark:hover:text-sky-400 rounded-xl"
          >
            <template #leading>
              <UIcon
                name="i-heroicons-calendar-days"
                class="w-5 h-5"
              />
            </template>
            Flight Operations
          </UButton>
        </nav>
      </div>

      <div class="mt-auto p-4 border-t border-gray-200 dark:border-gray-800/50">
        <UCard class="bg-gradient-to-br from-sky-500/10 to-indigo-500/10 border-0 shadow-none">
          <div class="flex items-center gap-3">
            <UAvatar
              src="https://api.dicebear.com/7.x/avataaars/svg?seed=Mert"
              alt="Mert"
              size="md"
              class="ring-2 ring-sky-500/50"
            />
            <div class="flex-1 min-w-0">
              <p class="text-sm font-semibold text-gray-900 dark:text-white truncate">
                Mert - Berkay
              </p>
              <p class="text-xs text-gray-500 truncate">
                System Admin
              </p>
            </div>
            <UColorModeButton />
          </div>
        </UCard>
      </div>
    </aside>

    <!-- Overlay for mobile sidebar -->
    <div
      v-if="isSidebarOpen"
      class="fixed inset-0 bg-gray-900/50 backdrop-blur-sm z-10 md:hidden"
      @click="isSidebarOpen = false"
    />

    <!-- Main Content -->
    <main class="flex-1 flex flex-col min-w-0 overflow-hidden bg-gray-50/50 dark:bg-gray-950/50">
      <header class="h-20 glass border-b border-gray-200/50 dark:border-gray-800/50 sticky top-0 z-10 flex items-center justify-between px-6 lg:px-10 transition-all duration-300">
        <div class="flex items-center gap-4">
          <UButton
            icon="i-heroicons-bars-3"
            variant="ghost"
            color="neutral"
            class="md:hidden"
            @click="isSidebarOpen = true"
          />
          <div class="hidden sm:block">
            <h2 class="text-lg font-semibold text-gray-800 dark:text-gray-100">
              {{ route.meta.title || 'Dashboard' }}
            </h2>
            <p class="text-xs text-gray-500">
              
            </p>
          </div>
        </div>

        <div class="flex items-center gap-4">
          <UButton
            icon="i-heroicons-bell"
            variant="ghost"
            color="neutral"
            class="relative"
          >
            <span class="absolute top-1 right-1 w-2 h-2 bg-rose-500 rounded-full ring-2 ring-white dark:ring-gray-900" />
          </UButton>
          <UButton
            icon="i-heroicons-cog-8-tooth"
            variant="ghost"
            color="neutral"
          />
        </div>
      </header>

      <div class="flex-1 overflow-y-auto p-6 lg:p-10">
        <div class="max-w-7xl mx-auto">
          <slot />
        </div>
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const isSidebarOpen = ref(false)
</script>
