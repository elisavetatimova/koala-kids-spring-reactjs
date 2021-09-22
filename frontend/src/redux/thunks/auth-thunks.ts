import {
    loginFailure,
    loginSuccess,
    logoutSuccess,
    registerFailure,
    registerSuccess,
    showLoader
} from "../actions/auth-actions";
import {reset} from "../actions/admin-actions";
import {UserData, UserRegistration, UserResetPasswordData} from "../../types/types";
import {Dispatch} from "redux";
import RequestService from '../../utils/request-service';

export const login = (userData: UserData, history: any) => async (dispatch: Dispatch) => {
    try {
        const response = await RequestService.post("/auth/login", userData);
        localStorage.setItem("email", response.data.email);
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("userRole", response.data.userRole);
        localStorage.setItem("isLoggedIn", "true");
        dispatch(loginSuccess(response.data.userRole));
        history.push("/account");
    } catch (error) {
        // @ts-ignore
        dispatch(loginFailure(error.response.data));
    }
};

export const registration = (userRegistrationData: UserRegistration) => async (dispatch: Dispatch) => {
    try {
        dispatch(showLoader());
        await RequestService.post("/registration", userRegistrationData);
        dispatch(registerSuccess());
    } catch (error) {
        // @ts-ignore
        dispatch(registerFailure(error.response.data));
    }
};

export const logout = () => async (dispatch: Dispatch) => {
    localStorage.removeItem("email");
    localStorage.removeItem("token");
    localStorage.removeItem("userRole");
    localStorage.removeItem("isLoggedIn");
    dispatch(logoutSuccess());
};

export const formReset = () => async (dispatch: Dispatch) => {
    dispatch(reset());
};
