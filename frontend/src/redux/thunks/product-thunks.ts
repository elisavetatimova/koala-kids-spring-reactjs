import {Dispatch} from "redux";

import {
    getProducts,
    fetchProductsByFilterParamsSuccess,
    fetchProductsByGenderSuccess,
    fetchProductsByProducerSuccess,
    fetchProductSuccess,
    loadingProduct
} from "../actions/product-actions";
import {FilterParamsType, Product} from "../../types/types";
import RequestService from '../../utils/request-service';

export const fetchProducts = () => async (dispatch: Dispatch) => {
    dispatch(loadingProduct());
    const response = await RequestService.get("/products");
    dispatch(getProducts(response.data));
};

export const fetchProduct = (id: string) => async (dispatch: Dispatch) => {
    dispatch(loadingProduct());
    const response = await RequestService.get("/products/" + id);
    dispatch(fetchProductSuccess(response.data));
};

export const fetchProductsByIds = (ids: Array<number>) => async (dispatch: Dispatch) => {
    dispatch(loadingProduct());
    const response = await RequestService.post("/products/ids", ids);
    dispatch(getProducts(response.data));
};

export const fetchProductsByFilterParams = (filter: FilterParamsType) => async (dispatch: Dispatch) => {
    dispatch(loadingProduct());
    const response = await RequestService.post("/products/search", filter);
    dispatch(fetchProductsByFilterParamsSuccess(response.data));
};

export const fetchProductsByGender = (gender: { productGender: string }) => async (dispatch: Dispatch) => {
    dispatch(loadingProduct());
    const response = await RequestService.post("/products/search/gender", gender);
    dispatch(fetchProductsByGenderSuccess(response.data));
};

export const fetchProductsByProducer = (producer: { producer: string }) => async (dispatch: Dispatch) => {
    dispatch(loadingProduct());
    const response = await RequestService.post("/products/search/producer", producer);
    dispatch(fetchProductsByProducerSuccess(response.data));
};
