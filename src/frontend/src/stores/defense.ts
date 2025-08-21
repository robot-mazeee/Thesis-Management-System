import { defineStore } from "pinia";
import DefenseDto from "@/models/DefenseDto";
import RemoteServices from "@/services/RemoteService";

export const useDefenseStore = defineStore('defense', {
    state: () => ({
        defenses: [] as DefenseDto[],
        loading: false as boolean
    }),
    actions: {
        async fetchDefenses() {
            console.log("getting defenses");
            this.loading = true;
            try {
                const data = await RemoteServices.getDefenses();
                console.log("Fetched defenses: ", data);
                this.defenses = data;
            } catch (error) {
                console.error("Error getting defenses: ", error);
            } finally {
                this.loading = false;
            }
        },

        async gradeDefense(defenseId: number, grade: number) {
            try {
                const defense = this.defenses.find(d => d.id === defenseId);
                if (!defense) 
                    throw new Error('Defense not found');

                defense.grade = grade;
                defense.status = 'SUBMITTED_TO_FENIX';
                const updatedDefense = await RemoteServices.gradeDefense(defense);
                console.log("updated defense: ", updatedDefense);

                const index = this.defenses.findIndex(d => d.id === defenseId);
                if (index !== -1) 
                    this.defenses[index] = updatedDefense;
            } catch (error) {
                console.error('Failed to grade defense:', error);
            }
        },

        async scheduleDefense(defenseId: number, date: Date) {}
    }
});