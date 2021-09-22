import {Product} from "../../types/types";

export const LOADING_PRODUCT = "LOADING_PRODUCT";
export const FETCH_PRODUCTS = "FETCH_PRODUCTS";
export const FETCH_PRODUCT_SUCCESS = "FETCH_PRODUCT_SUCCESS";
export const FETCH_PRODUCTS_BY_GENDER_SUCCESS = "FETCH_PRODUCTS_BY_GENDER_SUCCESS";
export const FETCH_PRODUCTS_BY_PRODUCER_SUCCESS = "FETCH_PRODUCTS_BY_PRODUCER_SUCCESS";
export const FETCH_PRODUCTS_BY_FILTER_PARAMS_SUCCESS = "FETCH_PRODUCTS_BY_FILTER_PARAMS_SUCCESS";
export const FETCH_PRODUCTS_BY_QUERY_SUCCESS = "FETCH_PRODUCTS_BY_QUERY_SUCCESS";
export const FETCH_PRODUCT_BY_QUERY_SUCCESS = "FETCH_PRODUCT_BY_QUERY_SUCCESS";

export type LoadingProductActionType = { type: typeof LOADING_PRODUCT};
export type GetProductsActionType = { type: typeof FETCH_PRODUCTS, payload: Array<Product> };
export type FetchProductSuccessActionType = { type: typeof FETCH_PRODUCT_SUCCESS, payload: Product };
export type FetchProductsByGenderSuccessActionType = { type: typeof FETCH_PRODUCTS_BY_GENDER_SUCCESS, payload: Array<Product> };
export type FetchProductsByProducerSuccessActionType = { type: typeof FETCH_PRODUCTS_BY_PRODUCER_SUCCESS, payload: Array<Product> };
export type FetchProductsByFilterParamsSuccessActionType = { type: typeof FETCH_PRODUCTS_BY_FILTER_PARAMS_SUCCESS, payload: Array<Product> };
export type FetchProductsByQuerySuccessActionType = { type: typeof FETCH_PRODUCTS_BY_QUERY_SUCCESS, payload: Array<Product> };
export type FetchProductByQuerySuccessActionType = { type: typeof FETCH_PRODUCT_BY_QUERY_SUCCESS, payload: Product };

export type ProductActionTypes = LoadingProductActionType |FetchProductsByQuerySuccessActionType |
    FetchProductByQuerySuccessActionType | FetchProductSuccessActionType | FetchProductsByGenderSuccessActionType |
    FetchProductsByProducerSuccessActionType | GetProductsActionType | FetchProductsByFilterParamsSuccessActionType;
