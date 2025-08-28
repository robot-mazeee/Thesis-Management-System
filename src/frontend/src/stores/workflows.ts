import { defineStore } from "pinia";
import WorkflowDto from "@/models/WorkflowDto";
import RemoteServices from "@/services/RemoteService";
import PersonDto from "@/models/PersonDto";

export const useWorkflowStore = defineStore('workflow', {
    state: () => ({
        workflows: [] as WorkflowDto[],
        loading: false as boolean
    }),
    actions: {
        async fetchWorkflows() {
            console.log("getting workflows");
            this.loading = true;
            try {
                const data = await RemoteServices.getWorkflows();
                console.log("Fetched workflows: ", data);
                this.workflows = data;
            } catch (error) {
                console.error("Error getting workflows: ", error);
            } finally {
                this.loading = false;
            }
        },

        async updateStatus(workflow: WorkflowDto, status: string) {
            try {
                workflow.status = status;
                console.log("Sending workflow: ", workflow);
                await RemoteServices.updateWorkflowStatus(workflow);
                await this.fetchWorkflows();
            } catch (error) {
                console.error("Workflow status update failed:", error);
            }
        },

        async saveJuriPresident(workflowId: number, president: PersonDto) {
            try {
                const workflow = this.getWorkflow(workflowId);

                workflow.juriPresident = president;
                workflow.status = 'JURI_PRESIDENT_ATTRIBUTED';
                const updatedWorkflow = await RemoteServices.selectJuriPresident(workflow);
                console.log("updated workflow: ", updatedWorkflow);

                this.updateWorkflow(workflowId, updatedWorkflow);
            } catch (error) {
                console.error('Failed to save Juri President:', error);
            }
        },

        async signDocument(workflowId: number) {
            try {
                const workflow = this.getWorkflow(workflowId);

                workflow.status = 'DOCUMENT_SIGNED';
                const updatedWorkflow = await RemoteServices.signDocument(workflow);
                console.log("updated workflow: ", updatedWorkflow);

                this.updateWorkflow(workflowId, updatedWorkflow);
            } catch (error) {
                console.error('Failed to sign document:', error);
            }
        },

        async linkDefense(workflowId: number, defenseId: number) {
            try {
                const workflow = this.getWorkflow(workflowId);

                workflow.defenseId = defenseId;
                console.log("workflow to link: ", workflow);
                const updatedWorkflow = await RemoteServices.linkDefense(workflow);
                console.log('updated workflow: ', updatedWorkflow);

                this.updateWorkflow(workflowId, updatedWorkflow);
            } catch (error) {
                console.error('Failed to link defense: ', error);
            }
        },

        getWorkflow(workflowId: number): WorkflowDto {
            const workflow = this.workflows.find(w => w.id === workflowId);
            if (!workflow) 
                throw new Error('Workflow not found');
            return workflow;
        },

        updateWorkflow(workflowId: number, updatedWorkflow: WorkflowDto) {
            const index = this.workflows.findIndex(w => w.id === workflowId);
            if (index !== -1) 
                this.workflows[index] = updatedWorkflow;
        }
    }
});