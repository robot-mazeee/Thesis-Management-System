import type PersonDto from "./PersonDto"

export default class WorkflowDto {
    id?: number
    defenseStatus?: string
    date?: string
    grade?: number
  
    constructor(obj?: Partial<WorkflowDto>) {
      Object.assign(this, obj)
    }
}