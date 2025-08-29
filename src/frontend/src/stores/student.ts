import type { PersonDto } from '@/models/PersonDto';
import { defineStore } from 'pinia';
import RemoteServices from '@/services/RemoteService';

export const useStudentStore = defineStore('studentStore', {
	state: () => ({
		student: {} as PersonDto,
		studentCreated: false,
		loggedIn: false,
	}),
	getters: {
		getId(): number {
			return this.student.id
		},
		getName(): string {
			return this.student.name
		},
		getEmail(): string {
			return this.student.email
		},
		getIstId(): string {
			return this.student.istId
		},
		getType(): string {
			return this.student.type
		},
		isLoggedIn(): boolean {
			return this.loggedIn
		},
		isStudentCreated(): boolean {
			return this.studentCreated;
		},
		currentStudent(): PersonDto {
			return this.student
		},
	},
	actions: {
		async login(studentId: number = 1) {
			this.student = await RemoteServices.getPerson(studentId);
			this.loggedIn = true;
		},

		logout() {
			this.student = {} as PersonDto;
			this.loggedIn = false;
		}
	},
	persist: true
})
