import {BarcodeInfo} from "../obj/BarcodeInfo";

export const BarcodeParserService = {
    parseOrder(barCode: string): BarcodeInfo {
        let strings = barCode.split('-');
        if (strings.length !== 3) {
            throw new Error(`Неизвестный штрих-код: ${barCode}`);
        }

        const orderNum = strings[0];
        const client = strings[2].replace("_", " ");

        const parts = strings[1].split("_");
        const part = parseInt(parts[0]);
        const partOf = parseInt(parts[1]);

        return {barCode, orderNum, part, partOf, client}
    },
}