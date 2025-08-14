import axios from 'axios'
import { useAppearanceStore } from '@/stores/appearance'
import DeiError from '@/models/DeiError'
import type PersonDto from '@/models/PersonDto'
import type WorkflowDto from '@/models/WorkflowDto'
import type DefenseDto from '@/models/DefenseDto'

const httpClient = axios.create()
httpClient.defaults.timeout = 50000
httpClient.defaults.baseURL = import.meta.env.VITE_ROOT_API
httpClient.defaults.headers.post['Content-Type'] = 'application/json'

export default class RemoteServices {
	// people
	static async getPeople(): Promise<PersonDto[]> {
		return httpClient.get('/people')
	}

	static async getPerson(id: Number): Promise<PersonDto> {
		console.log(`Getting person: ${id}`)
		return httpClient.get(`/people/${id}`)
	}

	static async getStudents(): Promise<PersonDto[]> {
		return httpClient.get('/students')
	}

	static async getProfessors(): Promise<PersonDto[]> {
		return httpClient.get('/professors')
	}

	static async createPerson(person: PersonDto): Promise<PersonDto> {
		return httpClient.post('/people', person)
	}

	static async editPerson(person: PersonDto) {
		return httpClient.put(`/people/${person.id}`, person);
	}

	static async deletePerson(person: PersonDto) {
		console.log(person.id)
		return httpClient.delete(`/people/${person.id}`);
	}

	// workflows
	static async createWorkflow(workflow: WorkflowDto) {
		return httpClient.post('/workflows', workflow);
	}

	static async getWorkflows(): Promise<WorkflowDto[]> {
		return httpClient.get('/workflows');
	}

	static async getWorkflow(workflow: WorkflowDto): Promise<WorkflowDto[]> {
		return httpClient.get(`/workflows/${workflow.id}`);
	}

	static async getWorkflowByStudent(studentId: number): Promise<WorkflowDto> {
		return httpClient.get(`/student-workflow/${studentId}`);
	}

	// Updates workflow status
	static async updateWorkflow(workflow: WorkflowDto): Promise<WorkflowDto> {
		return httpClient.put(`/workflows/${workflow.id}`, workflow);
	}

	static async signDocument(workflow: WorkflowDto): Promise<WorkflowDto> {
		return httpClient.put(`/workflows/${workflow.id}/sign-document`, workflow.id);
	}

	static async createDefense() {
		return httpClient.post('/defenses');
	}

	static async getDefenses(): Promise<DefenseDto[]> {
		return httpClient.get('/defenses');
	}

	static async updateDefense(defense: DefenseDto) {
		return httpClient.put(`/defenses/${defense.id}`, defense);
	}

	static async getJuriProposals(): Promise<WorkflowDto[]> {
		return httpClient.get('/workflows/juri-proposals');
	}

	static async getApprovedJuriProposals(): Promise<WorkflowDto[]> {
		return httpClient.get('/workflows/juri-proposals/approved');
	}

	static async selectJuriPresident(workflow: WorkflowDto): Promise<WorkflowDto> {
		return httpClient.put(`/workflows/${workflow.id}/juri-president`, workflow);
	}

	static async juriPresidentSelectedWorkflows(): Promise<WorkflowDto[]> {
		return httpClient.get(`/workflows/juri-president-selected`);
	}

	static async getSignedDocuments(): Promise<WorkflowDto[]> {
		return httpClient.get(`/workflows/signed-documents`);
	}

	static async getWorkflowSubmissions(): Promise<WorkflowDto[]> {
		return httpClient.get(`/workflows/submissions`);
	}

	static async errorMessage(error: any): Promise<string> {
		console.log(error);
		if (error.message === 'Network Error') {
			return 'Unable to connect to the server'
		} else if (error.message.split(' ')[0] === 'timeout') {
			return 'Request timeout - Server took too long to respond'
		} else {
			return error.response?.data?.message ?? 'Unknown Error'
		}
	}

	static async handleError(error: any): Promise<never> {
		const deiErr = new DeiError(
			await RemoteServices.errorMessage(error),
			error.response?.data?.code ?? -1
		)
		const appearance = useAppearanceStore()
		appearance.pushError(deiErr)
		appearance.loading = false
		throw deiErr
	}
}

httpClient.interceptors.request.use((request) => request, RemoteServices.handleError)
httpClient.interceptors.response.use((response) => response.data, RemoteServices.handleError)
