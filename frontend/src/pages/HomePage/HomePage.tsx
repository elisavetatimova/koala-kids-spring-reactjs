import React, {FC, useEffect} from 'react';

import {useDispatch} from "react-redux";
import {fetchCart} from "../../redux/thunks/cart-thunks";
const HomePage: FC = () => {
    const dispatch = useDispatch();

    useEffect(() => {
        const productsFromLocalStorage: Map<number, number> = new Map(JSON.parse(localStorage.getItem("products") as string));
        dispatch(fetchCart(Array.from(productsFromLocalStorage.keys())))
    }, []);

    return (
        <div>
            <h1 style={{textAlign: "center", paddingTop: "100px"}}>Pick the best toy for your kid!</h1>
        </div>
    );
};

export default HomePage;
