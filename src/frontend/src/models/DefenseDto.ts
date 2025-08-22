import type PersonDto from "./PersonDto"

export default class DefenseDto {
    id?: number
    status?: string
    date?: string
    grade?: number
    student?: PersonDto
  
    constructor(obj?: Partial<DefenseDto>) {
      	Object.assign(this, obj);
    }
}