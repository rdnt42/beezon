export interface CreateOrderRequest {
    orderNum: string,
    client: string,
    itemsCount: number,
    cellId?: number,
}