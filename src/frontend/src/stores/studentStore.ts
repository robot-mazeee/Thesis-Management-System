import type { PersonDto } from '@/models/PersonDto'
import { defineStore } from 'pinia'

export const useStudentStore = defineStore('studentStore', {
  state: () => ({
    student: {} as PersonDto,
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
    currentStudent(): PersonDto {
      return this.student
    }
  },
  actions: {
    login(newStudent: PersonDto) {
      this.student = newStudent
      this.loggedIn = true;
    },
    logout() {
      this.student = {} as PersonDto;
      this.loggedIn = false;
    }
  },
  persist: true
})
