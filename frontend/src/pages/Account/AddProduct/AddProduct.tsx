import React, {ChangeEvent, FC, FormEvent, useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {faPlusSquare} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

import ToastShow from "../../../component/Toasts/ToastShow";
import {addProduct, formReset} from "../../../redux/thunks/admin-thunks";
import {AppStateType} from "../../../redux/reducers/root-reducer";
import {ProductErrors, ProductRequest} from "../../../types/types";
import {fetchProducts} from "../../../redux/thunks/product-thunks";

type InitialStateType = {
    title: string
    producer: string
    year: number
    country: string
    type: string
    description: string
    productGender: string
    productTopNotes: string
    productMiddleNotes: string
    productBaseNotes: string
    price: number
    filename: string
    productRating: number
};

const AddProduct: FC = () => {
    const dispatch = useDispatch();
    const isProductAdded: boolean = useSelector((state: AppStateType) => state.admin.isProductAdded);
    const errors: Partial<ProductErrors> = useSelector((state: AppStateType) => state.admin.errors);

    const initialState: InitialStateType = {
        title: "",
        producer: "",
        year: 0,
        country: "",
        type: "",
        description: "",
        productGender: "",
        productTopNotes: "",
        productMiddleNotes: "",
        productBaseNotes: "",
        price: 0,
        filename: "",
        productRating: 0.0
    };

    const [{
        title,
        producer,
        year,
        country,
        type,
        description,
        productGender,
        productTopNotes,
        productMiddleNotes,
        productBaseNotes,
        price,
        filename,
        productRating
    }, setState] = useState(initialState);
    const [showToast, setShowToast] = useState(false);

    const {
        productTitleError,
        producerError,
        yearError,
        countryError,
        typeError,
        descriptionError,
        productGenderError,
        productTopNotesError,
        productMiddleNotesError,
        productBaseNotesError,
        priceError,
        filenameError
    } = errors;

    useEffect(() => {
        if (isProductAdded) {
            setState({...initialState});
            setShowToast(true);
            setTimeout(() => {
                setShowToast(false)
                dispatch(formReset());
            }, 5000);
            window.scrollTo(0, 0);
            dispatch(fetchProducts());
        }
    }, [isProductAdded]);

    const onFormSubmit = (event: FormEvent<HTMLFormElement>): void => {
        event.preventDefault();

        const productRequest: ProductRequest = {
            title: title,
            producer: producer,
            year: year,
            country: country,
            type: type,
            productGender: productGender,
            productTopNotes: productTopNotes,
            productMiddleNotes: productMiddleNotes,
            productBaseNotes: productBaseNotes,
            description: description,
            filename: filename,
            price: price,
            productRating: productRating
        }
        dispatch(addProduct(productRequest));
    };

    const handleFileChange = (event: any): void => {
        setState(prevState => ({...prevState, file: event.target.files[0]}));
    };

    const handleInputChange = (event: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLSelectElement>): void => {
        const {name, value} = event.target;
        setState(prevState => ({...prevState, [name]: value}));
    };

    return (
        <>
            <ToastShow showToast={showToast} message={"Toy successfully added!"}/>
            <div className="container">
                <h4><FontAwesomeIcon className="mr-2" icon={faPlusSquare}/>Add product</h4>
                <br/>
                <form onSubmit={onFormSubmit}>
                    <div className="form row">
                        <div className="col">
                            <label>TOY title: </label>
                            <input
                                type="text"
                                className={productTitleError ? "form-control is-invalid" : "form-control"}
                                name="title"
                                value={title}
                                placeholder="Enter the product title"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{productTitleError}</div>
                        </div>
                        <div className="col">
                            <label>Manufacturer: </label>
                            <input
                                type="text"
                                className={producerError ? "form-control is-invalid" : "form-control"}
                                name="producer"
                                value={producer}
                                placeholder="Enter the brand"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{producerError}</div>
                        </div>
                    </div>
                    <div className="form row mt-3">
                        <div className="col">
                            <label>Production Year: </label>
                            <input
                                type="text"
                                className={yearError ? "form-control is-invalid" : "form-control"}
                                name="year"
                                value={year}
                                placeholder="Enter the release year"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{yearError}</div>
                        </div>
                        <div className="col">
                            <label>Country: </label>
                            <input
                                type="text"
                                className={countryError ? "form-control is-invalid" : "form-control"}
                                name="country"
                                value={country}
                                placeholder="Enter the manufacturer country"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{countryError}</div>
                        </div>
                    </div>
                    <div className="form row mt-3">
                        <div className="col">
                            <label>TOY type: </label>
                            <select name="type"
                                    className={typeError ? "form-control is-invalid" : "form-control"}
                                    onChange={handleInputChange}>
                                <option hidden={true} value=""></option>
                                <option value="Toy">Toy</option>
                            </select>
                            <div className="invalid-feedback">{typeError}</div>
                        </div>
                        <div className="col">
                            <label>Description</label>
                            <input
                                type="text"
                                className={descriptionError ? "form-control is-invalid" : "form-control"}
                                name="description"
                                value={description}
                                placeholder="Enter the description"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{descriptionError}</div>
                        </div>
                    </div>
                    <div className="form row mt-3">
                        <div className="col">
                            <label>Gender: </label>
                            <select name="productGender"
                                    className={productGenderError ? "form-control is-invalid" : "form-control"}
                                    onChange={handleInputChange}>
                                <option hidden={true} value=""></option>
                                <option value="male">male</option>
                                <option value="female">female</option>
                                <option value="unisex">unisex</option>
                            </select>
                            <div className="invalid-feedback">{productGenderError}</div>
                        </div>
                        <div className="col">
                            <label>Top notes: </label>
                            <input
                                type="text"
                                className={productTopNotesError ? "form-control is-invalid" : "form-control"}
                                name="productTopNotes"
                                value={productTopNotes}
                                placeholder="Enter the top notes"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{productTopNotesError}</div>
                        </div>
                    </div>
                    <div className="form row mt-3">
                        <div className="col">
                            <label>Product Middle Notes:</label>
                            <input
                                type="text"
                                className={productMiddleNotesError ? "form-control is-invalid" : "form-control"}
                                name="productMiddleNotes"
                                value={productMiddleNotes}
                                placeholder="Enter the middle notes"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{productMiddleNotesError}</div>
                        </div>
                        <div className="col">
                            <label>Product Base Notes:</label>
                            <input
                                type="text"
                                className={productBaseNotesError ? "form-control is-invalid" : "form-control"}
                                name="productBaseNotes"
                                value={productBaseNotes}
                                placeholder="Enter the base notes"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{productBaseNotesError}</div>
                        </div>
                    </div>
                    <div className="form row mt-3">
                        <div className="col">
                            <label>Price: </label>
                            <input
                                type="text"
                                className={priceError ? "form-control is-invalid" : "form-control"}
                                name="price"
                                value={price}
                                placeholder="Enter the price"
                                onChange={handleInputChange}/>
                            <div className="invalid-feedback">{priceError}</div>
                        </div>
                        <div className="col">
                            <label>Image: </label>
                            <input
                                type="text"
                                className={filenameError ? "form-control is-invalid" : "form-control"}
                                name="filename"
                                value={filename}
                                placeholder="Enter the public url to the image."
                                onChange={handleInputChange}/>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-dark mt-3">
                        <FontAwesomeIcon className="mr-2" icon={faPlusSquare}/>Add
                    </button>
                </form>
            </div>
        </>
    );
};

export default AddProduct;
