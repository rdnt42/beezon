import {OrderPart} from "./OrderPart";
import {Cell} from "./Cell";

export interface Order {
    id: string,
    client: string,
    itemsCount: number,
    parts?: OrderPart[],
    cell?: Cell,
    dsc?: string,
}