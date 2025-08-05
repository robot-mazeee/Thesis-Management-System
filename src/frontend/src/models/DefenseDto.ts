export default class WorkflowDto {
    id?: number
    status?: string
    date?: string
    grade?: number
  
    constructor(obj?: Partial<WorkflowDto>) {
      Object.assign(this, obj)
    }
}