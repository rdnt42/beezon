export interface Delivery {
    description: string,
    status: string,
    performer: string,
    whoRequested: string,
    addressFrom: string,
    phoneFrom?: string,
    addressTo: string,
    phoneTo?: string,
    commentTo?: string,

    updatedDate: Date
}