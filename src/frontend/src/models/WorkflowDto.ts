import type PersonDto from "./PersonDto"

export default class WorkflowDto {
    id?: number
    workflowStatus?: string
    professors?: PersonDto[]
    student?: PersonDto
    juriPresident?: PersonDto
  
    constructor(obj?: Partial<WorkflowDto>) {
      Object.assign(this, obj)
    }
}