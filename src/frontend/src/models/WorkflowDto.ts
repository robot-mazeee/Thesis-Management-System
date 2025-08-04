import type PersonDto from "./PersonDto"

export default class WorkflowDto {
    id?: number
    workflowStatus?: string
    professors?: PersonDto[]
    studentId?: number
    juriPresident?: PersonDto
  
    constructor(obj?: Partial<WorkflowDto>) {
      Object.assign(this, obj)
    }
}