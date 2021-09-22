import React, {FC, useEffect, useState} from "react";
import {Route, useLocation} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";

import Checkbox from "../../component/CheckBox/Checkbox";
import CheckboxRadio from "../../component/CheckboxRadio/CheckboxRadio";
import MenuCards from "../../component/MenuCards/MenuCards";
import {gender, producer, price} from "./MenuData";
import {
    fetchProducts,
    fetchProductsByFilterParams,
    fetchProductsByGender,
    fetchProductsByProducer
} from "../../redux/thunks/product-thunks";
import "./MenuStyle.css";
import {AppStateType} from "../../redux/reducers/root-reducer";
import {FilterParamsType, Product} from "../../types/types";
import ScrollButton from "../../component/ScrollButton/ScrollButton";

const Menu: FC = () => {
    const dispatch = useDispatch();
    const products: Array<Product> = useSelector((state: AppStateType) => state.product.products);
    const loading: boolean = useSelector((state: AppStateType) => state.product.isProductLoading);
    const [filterParams, setFilterParams] = useState<FilterParamsType>({
        producers: [],
        genders: [],
        prices: []
    });
    const [sortByPrice, setSortByPrice] = useState<boolean>();
    const {state} = useLocation<{ id: string }>();

    useEffect(() => {
        const productData: string = state.id;

        if (productData === "female" || productData === "male") {
            dispatch(fetchProductsByGender({productGender: productData}));
            window.scrollTo(0, 0);
        } else if (productData === "all") {
            dispatch(fetchProducts());
            window.scrollTo(0, 0);
        } else {
            dispatch(fetchProductsByProducer({producer: productData}));
            window.scrollTo(0, 0);
        }
    }, []);

    const getProducts = (variables: FilterParamsType): void => {
        dispatch(fetchProductsByFilterParams(variables));
    };

    const handlePrice = (value: number): Array<number> => {
        let find = price.find((item) => item.id == value);
        return find!.array;
    };

    const handleFilters = (filters: Array<string> | number, category: string): void => {
        const newFilters: any = filterParams;
        newFilters[category] = filters;

        if (category === "prices") {
            let priceValues = handlePrice(filters as number);
            newFilters[category] = priceValues;
        }
        getProducts({...newFilters, sortByPrice})
        setFilterParams(newFilters);
    };

    const handleSortByPrice = (sortedBy: boolean, event: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void => {
        event.preventDefault();

        setSortByPrice(sortedBy);
        getProducts({...filterParams, sortByPrice: sortedBy});
    };

    return (
        <div className="container d-flex">
            <ScrollButton/>
            <nav id="sidebar">
                <div className="sidebar-header">
                    <h3>Toys</h3>
                </div>
                <ul className="list-unstyled components">
                    <h5>Manufacturer</h5>
                    <li className="active mb-2" id="homeSubmenu">
                        <Checkbox list={producer}
                                  handleFilters={(filters) => handleFilters(filters, "producers")}/>
                    </li>
                    <h5>Gender</h5>
                    <li className="active mb-2">
                        <Checkbox list={gender}
                                  handleFilters={(filters) => handleFilters(filters, "genders")}/>
                    </li>
                    <h5>Price</h5>
                    <li className="active mb-2">
                        <CheckboxRadio list={price}
                                       handleFilters={(filters) => handleFilters(filters, "prices")}/>
                    </li>
                </ul>
            </nav>
            <Route exact component={() =>
                <MenuCards
                    data={products}
                    loading={loading}
                    itemsPerPage={16}
                    searchByData={[
                        {label: 'Type', value: 'title'},
                        {label: 'Manufacturer ', value: 'country'}]}
                    sortByPrice={sortByPrice}
                    handleSortByPrice={handleSortByPrice}/>}/>
        </div>
    );
};

export default Menu;
