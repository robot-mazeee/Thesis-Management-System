import type PersonDto from "./PersonDto"

export default class WorkflowDto {
    id?: number
    status?: string
    date?: string
    grade?: number
    student?: PersonDto
  
    constructor(obj?: Partial<WorkflowDto>) {
      	Object.assign(this, obj);
    }
}