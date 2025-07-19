import type { PersonDto } from '@/models/PersonDto'
import { defineStore } from 'pinia'
import RemoteServices from '@/services/RemoteService'

export const useStudentStore = defineStore('studentStore', {
  state: () => ({
    student: {} as PersonDto,
    studentCreated: false,
    loggedIn: false,
  }),
  getters: {
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
    }
  },
  actions: {
    login(newStudent: PersonDto) {
        // this.student = newStudent
        this.loggedIn = true;
    },
    logout() {
        // this.student = {} as PersonDto;
        this.loggedIn = false;
    },
    async createGenericStudent() {
      this.student = await RemoteServices.createPerson({
        name: 'John Doe',
        istId: '110283',
        email: 'johndoe@tecnico.ulisboa.pt',
        type: 'STUDENT',
      });
      this.studentCreated = true;
    }
  },
  persist: true
})
