import {Dispatch} from "redux";

import {
    addProductFailure,
    addProductSuccess,
    getAllUsers,
    getAllUsersOrders,
    getUserInfo,
    getUserOrders,
    reset,
    updateProductFailure,
    updateProductSuccess,
    loadingData,
} from "../actions/admin-actions";
import {fetchProductSuccess, getProducts} from "../actions/product-actions";
import RequestService from '../../utils/request-service';
import {ProductRequest} from "../../types/types";

export const addProduct = (productRequest: ProductRequest) => async (dispatch: Dispatch) => {
    try {
        await RequestService.post("/admin/add", productRequest, true)
        dispatch(addProductSuccess());
    } catch (err: unknown) {
        // @ts-ignore
        dispatch(addProductFailure(err.response.data));
    }
};

export const updateProduct = (productRequest:ProductRequest) => async (dispatch: Dispatch) => {
    try {
        const response = await RequestService.post("/admin/edit", productRequest, true);
        dispatch(updateProductSuccess());
        dispatch(fetchProductSuccess(response.data));
    } catch (error: unknown) {
        // @ts-ignore
        dispatch(updateProductFailure(error.response.data));
    }
};

export const deleteProduct = (id?: number) => async (dispatch: Dispatch) => {
    const response = await RequestService.delete("/admin/delete/" + id, true);
    dispatch(getProducts(response.data));
};

export const fetchAllUsersOrders = () => async (dispatch: Dispatch) => {
    dispatch(loadingData());
    const response = await RequestService.get("/admin/orders", true);
    dispatch(getAllUsersOrders(response.data));
};

export const fetchUserOrders = (email: string | undefined) => async (dispatch: Dispatch) => {
    const response = await RequestService.post("/admin/order", {email: email}, true);
    dispatch(getUserOrders(response.data));
};

export const fetchAllUsers = () => async (dispatch: Dispatch) => {
    dispatch(loadingData());
    const response = await RequestService.get("/admin/user/all", true);
    dispatch(getAllUsers(response.data));
};

export const fetchUserInfo = (id: string) => async (dispatch: Dispatch) => {
    dispatch(loadingData());
    const response = await RequestService.get("/admin/user/" + id, true);
    dispatch(getUserInfo(response.data));
};

export const formReset = () => async (dispatch: Dispatch) => {
    dispatch(reset());
};
