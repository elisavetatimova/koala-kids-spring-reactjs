import {ProductPrice} from "../../types/types";

export const producer: Array<{ name: string }> = [
    {"name": "Creative Engineering"},
    {"name": "Green Toys"},
    {"name": "Lego"},
    {"name": "Barbie"},
    {"name": "Bandai Namco"},
    {"name": "Fisher-Price"},
    {"name": "Nerf"},
    {"name": "Nasbro"},
    {"name": "My Little Pony"},
    {"name": "Mobile Suit Gundam"},
    {"name": "Hot Wheels"},
    {"name": "Mattel"},
    {"name": "Transformers"},
    {"name": "Littlest Pet Shop"},
    {"name": "Playskool"},
    {"name": "Frozen"},
    {"name": "Mega Blocks"},
    {"name": "Tomy Company"},
];

export const gender: Array<{ name: string }> = [
    {"name": "male"},
    {"name": "female"},
    {"name": "unisex"}
];

export const price: Array<ProductPrice> = [
    {"id": 1, "name": "any", "array": []},
    {"id": 2, "name": "15 - 25 $", "array": [15, 25]},
    {"id": 3, "name": "25 - 40 $", "array": [25, 40]},
    {"id": 4, "name": "40 - 90 $", "array": [40, 90]},
    {"id": 5, "name": "90 - 175+ $", "array": [90, 250]}
];
