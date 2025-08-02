import { defineStore } from 'pinia'

export const useRoleStore = defineStore('role', {
  state: () => ({
    currentRole: 'staff',
  }),
  getters: {
    isStaff(): boolean {
      return this.currentRole === 'staff'
    },
    isStudent(): boolean {
      return this.currentRole === 'student'
    },
    isCoordinator(): boolean {
      return this.currentRole === 'coordinator'
    },
    isProfessor(): boolean {
      return this.currentRole === 'professor'
    },
    isAdmin(): boolean {
      return this.currentRole === 'admin'
    },
    currentActiveRole(): string {
        return this.currentRole
    }
  },
  persist: true
})