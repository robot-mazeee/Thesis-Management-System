<template>
  <v-row align="center">
    <v-col>
      <h2 class="text-left ml-1">{{ showStudents ? "Listagem de Alunos" : "Listagem de Pessoas"}}</h2>
    </v-col>
    <v-col cols="auto">
      <PersonDialog 
        :editMode="editing"
        :personToEdit="selectedPerson"
        @close-dialog="handleDialogClose"
      />
    </v-col>
  </v-row>

  <v-btn @click="toggleView" color="primary" class="mb-3">
    {{ showStudents ? "Todas as Pessoas" : "Filtrar Alunos" }}
  </v-btn>

  <v-text-field
    v-model="search"
    label="Search"
    prepend-inner-icon="mdi-magnify"
    variant="outlined"
    hide-details
    single-line
  ></v-text-field>

  <v-data-table
    :headers="showStudents ? studentsHeaders : peopleHeaders "
    :items="showStudents ? students : people"
    :search="search"
    :loading="loading"
    :custom-filter="fuzzySearch"
    item-key="id"
    class="text-left"
    no-data-text="Sem pessoas a apresentar."
  >
    <template v-slot:[`item.type`]="{ item }">
      <v-chip :color="getColorByType(item.type)" text-color="white">
        {{ translateType(item.type) }}
      </v-chip>
    </template>
    <template v-slot:[`item.actions`]="{ item }">
      <v-tooltip>
        <template v-slot:activator="{ props }">
          <v-icon v-bind="{ props }" @click="openEditDialog(item)" class="mr-2">mdi-pencil</v-icon>
        </template>
      </v-tooltip>
      <v-icon @click="deletePerson(item)">mdi-delete</v-icon>
    </template>
    <template v-slot:[`item.workflowState`]="{ item }">
      <v-chip :color="getColorByStatus(item.workflowState)" text-color="white">
        {{ translateStatus(item.workflowState) }}
      </v-chip>
    </template>
    <template v-slot:[`item.profile`]="{ item }">
      <v-btn @click="goToProfile(item.id)" color="info" class="mb-3">
        Go to Profile
      </v-btn>
    </template>

  </v-data-table>

  <GoHomeBtn />

</template>

<script setup lang="ts">
import GoHomeBtn from '../../components/GoHomeBtn.vue'
import RemoteService from '@/services/RemoteService'
import PersonDialog from './PersonDialog.vue'
import { reactive, ref } from 'vue'
import PersonDto from '../../models/PersonDto'
import { useRouter } from 'vue-router'

let search = ref('')
let loading = ref(true)
let showStudents = ref(false)
const editing = ref(false);

const peopleHeaders = [
  { title: 'ID', key: 'id', value: 'id', sortable: true, filterable: false },
  {
    title: 'Nome',
    key: 'name',
    value: 'name',
    sortable: true,
    filterable: true
  },
  {
    title: 'IST ID',
    key: 'istId',
    value: 'istId',
    sortable: true,
    filterable: true
  },
  {
    title: 'Email',
    key: 'email',
    value: 'email',
    sortable: true,
    filterable: true
  },
  {
    title: 'Tipo',
    key: 'type',
    value: 'type',
    sortable: true,
    filterable: true
  },
  {
    title: 'Ações',
    key: 'actions',
    value: 'actions',
    sortable: false,
    filterable: false
  }
  // TODO: maybe add another column with possible actions? (edit / delete)
]

const studentsHeaders = [
  { title: 'ID', key: 'id', value: 'id', sortable: true, filterable: false },
  {
    title: 'Nome',
    key: 'name',
    value: 'name',
    sortable: true,
    filterable: true
  },
  {
    title: 'IST ID',
    key: 'istId',
    value: 'istId',
    sortable: true,
    filterable: true
  },
  {
    title: 'Email',
    key: 'email',
    value: 'email',
    sortable: true,
    filterable: true
  },
  {
    title: 'Estado do Workflow',
    key: 'workflowState',
    value: 'workflowState',
    sortable: true,
    filterable: true
  },
  {
    title: 'Profile',
    key: 'profile',
    value: 'profile',
    sortable: false,
    filterable: false
  },
]

const people: PersonDto[] = reactive([])
const students: PersonDto[] = reactive([])

// prop for dialogs
let selectedPerson = ref<PersonDto>({})

const router = useRouter()

getPeopleOrStudents() // first render

const toggleView = () => {
  showStudents.value = !showStudents.value;
  getPeopleOrStudents(); // Fetch data again based on the new value
};

async function getPeopleOrStudents() {  
  if (showStudents.value) {
    students.splice(0, students.length)
    students.push(...(await RemoteService.getStudents()))
  }
  else {
    people.splice(0, people.length)
    people.push(...(await RemoteService.getPeople()))
  }

  loading.value = false
}

function openEditDialog(person: PersonDto) {
  console.log("opening");
  selectedPerson.value = person;
  editing.value = true;
}

const handleDialogClose = () => {
  console.log("closing dialog")
  selectedPerson.value = {};
  editing.value = false;
  getPeopleOrStudents();
};

const deletePerson = async (person: PersonDto) => {
  console.log("A apagar pessoa:", person);
  try {
    await RemoteService.deletePerson(person);
    getPeopleOrStudents();
  } catch (error) {
    console.error("Erro ao apagar pessoa:", error);
  }
};

const goToProfile = (id: number) => {
    router.push(`/people/${id}`);
};


const getColorByType = (type: string) => {
  switch (type) {
    case "COORDINATOR":
      return "purple";
    case "STAFF":
      return "red";
    case "TEACHER":
      return "blue";
    case "SC":
      return "yellow";
    default:
      return "green";
  }
};

const getColorByStatus = (status: string) => {
  switch (status) {
    case "APPROVED_BY_SC":
      return "purple";
    case "JURI_PRESIDENT_ATTRIBUTED":
      return "red";
    case "DOCUMENT_SIGNED":
      return "green";
    case "SUBMITTED_TO_FENIX":
      return "yellow";
    default:
      return "blue"
  }
};

const translateStatus = (status: string) => {
  switch (status) {
    case "JURI_PROPOSAL_SUBMITTED":
      return "Juri Proposal Submitted"
    case "APPROVED_BY_SC":
      return "Approved By SC";
    case "JURI_PRESIDENT_ATTRIBUTED":
      return "Juri President Attributed";
    case "DOCUMENT_SIGNED":
      return "Document Signed";
    case "SUBMITTED_TO_FENIX":
      return "Submitted to Fenix";
    default:
      return "No workflows initiated"
  }
};

const translateType = (type: string) => {
  switch (type) {
    case "COORDINATOR":
      return "Coordenador";
    case "STAFF":
      return "Staff";
    case "TEACHER":
      return "Professor";
    case "SC":
      return "SC";
    default:
      return "Aluno";
  }
};

// const deletePerson = (person: PeopleDto) => {
//   console.log('Deleting person:', person);
//   RemoteService.deletePerson(person.id)
//     .then(() => console.log(`Person with ID ${person.id} deleted successfully`))
//     .catch((error) => console.error('Error deleting person:', error));
// }

const fuzzySearch = (value: string, search: string) => {
  // Regex to match any character in between the search characters
  let searchRegex = new RegExp(search.split('').join('.*'), 'i')
  return searchRegex.test(value)
}

</script>
