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
    async login() {
      console.log("before login - ", "studentCreated: ", this.studentCreated, "islogged in?: : ", this.loggedIn)
      if (!this.studentCreated) {
        await this.createGenericStudent();
      }
      this.loggedIn = true;
      console.log("Login student id: ", this.student.id)
    },
    logout() {
        this.student = {} as PersonDto;
        this.loggedIn = false;
    },
    async createGenericStudent() {
      console.log("creating student")
      try {
        this.student = await RemoteServices.createPerson({
          name: 'John Doe',
          istId: '110283',
          email: 'johndoe@tecnico.ulisboa.pt',
          type: 'STUDENT',
        } as PersonDto);
        this.studentCreated = true;
        console.log("Generic student: ", this.student)
      } catch (error) {
        console.log("Error creating generic student: ", error);
      }
    },
    async updateCurrentStudent(studentId: number) {
      this.student = await RemoteServices.getPerson(studentId);
    }
  },
  persist: true
})
