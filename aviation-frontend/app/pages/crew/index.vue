<script setup>
import { ref, reactive } from 'vue'

// 1. Veriyi Çekme
const { data: crewList, status, refresh } = useFetch('http://localhost:8080/crew-service/api/crews', { lazy: true })

const columns = [
  { accessorKey: 'id', header: 'ID' },
  { accessorKey: 'name', header: 'Full Name' },
  { accessorKey: 'role', header: 'Role' },
  { accessorKey: 'status', header: 'Status' },
  { id: 'actions', header: 'Actions' }

]

// 2. Form ve Modal Durumları (State)
const isModalOpen = ref(false)
const isEditing = ref(false)
const formState = reactive({
  id: null,
  name: '',
  role: 'Pilot',
  status: 'AVAILABLE'
})

const roleOptions = ['Pilot', 'Co-Pilot', 'Engineer', 'Cabin Crew']
const statusOptions = ['AVAILABLE', 'UNAVAILABLE', 'ON_FLIGHT', 'ON_LEAVE', 'RESTING']

// 3. Ekleme Pop-up'ını Aç
function openAddModal() {
  isEditing.value = false
  formState.id = null
  formState.name = ''
  formState.role = 'Pilot'
  formState.status = 'AVAILABLE'
  isModalOpen.value = true
}

// 4. Düzenleme Pop-up'ını Aç
function openEditModal(row) {
  isEditing.value = true
  formState.id = row.original.id
  formState.name = row.original.name
  formState.role = row.original.role
  formState.status = row.original.status
  isModalOpen.value = true
}

// 5. Kaydet Butonuna Basıldığında (POST veya PUT)
async function savePersonnel() {
  const url = isEditing.value
    ? `http://localhost:8080/crew-service/api/crews/${formState.id}`
    : 'http://localhost:8080/crew-service/api/crews'

  const method = isEditing.value ? 'PUT' : 'POST'

  // Backend'e isteği atıyoruz
  await $fetch(url, {
    method: method,
    body: {
      name: formState.name,
      role: formState.role,
      status: formState.status
    }
  })

  isModalOpen.value = false // Modalı kapat
  await refresh() // Tabloyu anında yenile!
  
  const toast = useToast()
  toast.add({
    title: 'Personnel Saved',
    description: `${formState.name} has been saved.`,
    color: 'success',
    icon: 'i-heroicons-check-circle'
  })
}

// 6. Silme İşlemi (DELETE)
async function deletePersonnel(id) {
  if (confirm('Are you sure you want to delete this personnel?')) {
    await $fetch(`http://localhost:8080/crew-service/api/crews/${id}`, {
      method: 'DELETE'
    })
    await refresh() // Tabloyu anında yenile
  }
}
</script>

<template>
  <div>
    <div class="flex justify-between items-center mb-6">
      <div>
        <h2 class="text-2xl font-bold text-gray-900 dark:text-white">
          Crew Management
        </h2>
        <p class="text-gray-500 text-sm">
          Monitor and manage flight personnel status and assignments.
        </p>
      </div>
      <UButton
        icon="i-heroicons-plus"
        label="Add New Personnel"
        color="primary"
        @click="openAddModal"
      />
    </div>

    <UCard shadow="sm">
      <UTable
        :columns="columns"
        :data="crewList || []"
        :loading="status === 'pending'"
      >
        <template #status-cell="{ row }">
          <UBadge
            :color="row.original.status === 'AVAILABLE' ? 'success' : row.original.status === 'ON_FLIGHT' ? 'sky' : 'warning'"
            variant="subtle"
          >
            {{ row.original.status }}
          </UBadge>
        </template>

        <template #actions-cell="{ row }">
          <div class="flex gap-2">
            <UButton
              icon="i-heroicons-pencil-square"
              size="xs"
              color="neutral"
              variant="ghost"
              title="Edit"
              @click="openEditModal(row)"
            />
            <UButton
              icon="i-heroicons-trash"
              size="xs"
              color="red"
              variant="ghost"
              title="Delete"
              @click="deletePersonnel(row.original.id)"
            />
          </div>
        </template>
      </UTable>
    </UCard>

    <UModal v-model="isModalOpen" prevent-close>
      <UCard :ui="{ ring: '', divide: 'divide-y divide-gray-100 dark:divide-gray-800' }">
        <template #header>
          <div class="flex items-center justify-between">
            <h3 class="text-base font-semibold leading-6 text-gray-900 dark:text-white">
              {{ isEditing ? 'Edit Personnel' : 'Add New Personnel' }}
            </h3>
            <UButton
              color="neutral"
              variant="ghost"
              icon="i-heroicons-x-mark-20-solid"
              class="-my-1"
              @click="isModalOpen = false"
            />
          </div>
        </template>

        <form
          class="space-y-4"
          @submit.prevent="savePersonnel"
        >
          <UFormField label="Full Name">
            <UInput
              v-model="formState.name"
              placeholder="e.g. Sabiha Gökçen"
              required
            />
          </UFormField>

          <UFormField label="Role">
            <USelect v-model="formState.role" :items="roleOptions" />
          </UFormField>

          <UFormField label="Status">
            <USelect v-model="formState.status" :items="statusOptions" />
          </UFormField>

          <div class="flex justify-end gap-3 mt-6">
            <UButton
              label="Cancel"
              color="neutral"
              variant="ghost"
              @click="isModalOpen = false"
            />
            <UButton
              type="submit"
              label="Save Personnel"
              color="primary"
            />
          </div>
        </form>
      </UCard>
    </UModal>
  </div>
</template>
