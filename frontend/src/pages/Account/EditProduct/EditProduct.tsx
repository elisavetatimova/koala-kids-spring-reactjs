import React, {ChangeEvent, FC, FormEvent, useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEdit} from "@fortawesome/free-solid-svg-icons";
import {RouteComponentProps} from 'react-router-dom';

import {fetchProduct, fetchProducts} from "../../../redux/thunks/product-thunks";
import {formReset, updateProduct} from "../../../redux/thunks/admin-thunks";
import {AppStateType} from "../../../redux/reducers/root-reducer";
import {Product, ProductErrors, ProductRequest} from "../../../types/types";
import ToastShow from "../../../component/Toasts/ToastShow";

const EditProduct: FC<RouteComponentProps<{ id: string }>> = ({match}) => {
    const dispatch = useDispatch();
    const productData: Partial<Product> = useSelector((state: AppStateType) => state.product.product);
    const errors: Partial<ProductErrors> = useSelector((state: AppStateType) => state.admin.errors);
    const isProductEdited: boolean = useSelector((state: AppStateType) => state.admin.isProductEdited);
    const [product, setProduct] = useState<Partial<Product>>(productData);
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

    const {
        id,
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
    } = product;

    useEffect(() => {
        dispatch(fetchProduct(match.params.id));
    }, []);

    useEffect(() => {
        setProduct(productData);
        if (isProductEdited) {
            setShowToast(true);
            setTimeout(() => {
                setShowToast(false)
                dispatch(formReset());
            }, 5000);
            window.scrollTo(0, 0);
            dispatch(fetchProducts());
        }
    }, [productData]);

    const onFormSubmit = (event: FormEvent<HTMLFormElement>): void => {
        event.preventDefault();
        const editProductRequest: ProductRequest = {
            id: id,
            title: title!,
            producer: producer!,
            year: year!,
            country: country!,
            type: type!,
            productGender: productGender!,
            productTopNotes: productTopNotes!,
            productMiddleNotes: productMiddleNotes!,
            productBaseNotes: productBaseNotes!,
            description: description!,
            filename: filename!,
            price: price!
        }
        dispatch(updateProduct(editProductRequest));
    };

    const handleInputChange = (event: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLSelectElement>): void => {
        const {name, value} = event.target;
        setProduct({...product, [name]: value});
    };

    return (
        <>
            <ToastShow showToast={showToast} message={"Toy successfully edited!"}/>
            <div className="container">
                <h4><FontAwesomeIcon className="mr-2" icon={faEdit}/>Edit product</h4>
                <form onSubmit={onFormSubmit}>
                    <div className="row mt-5">
                        <div className="col-md-6">
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Product title: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={productTitleError ? "form-control is-invalid" : "form-control"}
                                        name="title"
                                        value={title}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{productTitleError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Brand: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={producerError ? "form-control is-invalid" : "form-control"}
                                        name="producer"
                                        value={producer}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{producerError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Release year: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={yearError ? "form-control is-invalid" : "form-control"}
                                        name="year"
                                        value={year}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{yearError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Country: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={countryError ? "form-control is-invalid" : "form-control"}
                                        name="country"
                                        value={country}
                                        onChange={handleInputChange}/>

                                    <div className="invalid-feedback">{countryError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Product type: </label>
                                <div className="col-sm-8">
                                    <select name="type"
                                            className={typeError ? "form-control is-invalid" : "form-control"}
                                            onChange={handleInputChange}>
                                        {productData.type === "Toy" ?
                                            <>
                                                <option value={productData.type}>{productData.type}</option>
                                            </> :
                                            <>
                                                <option value="Toy">Toy</option>
                                            </>}
                                    </select>
                                    <div className="invalid-feedback">{typeError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Description: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={descriptionError ? "form-control is-invalid" : "form-control"}
                                        name="description"
                                        value={description}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{descriptionError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Gender: </label>
                                <div className="col-sm-8">
                                    <select name="productGender"
                                            className={productGenderError ? "form-control is-invalid" : "form-control"}
                                            onChange={handleInputChange}>
                                        {productData.productGender === "male" ?
                                            <>
                                                <option value={productData.productGender}>{productData.productGender}</option>
                                                <option value="female">female</option>
                                            </> :
                                            <>
                                                <option value={productData.productGender}>{productData.productGender}</option>
                                                <option value="male">male</option>
                                            </>}
                                    </select>
                                    <div className="invalid-feedback">{productGenderError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Top notes: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={productTopNotesError ? "form-control is-invalid" : "form-control"}
                                        name="productTopNotes"
                                        value={productTopNotes}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{productTopNotesError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Heart notes: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={productMiddleNotesError ? "form-control is-invalid" : "form-control"}
                                        name="productMiddleNotes"
                                        value={productMiddleNotes}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{productMiddleNotesError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Base notes: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={productBaseNotesError ? "form-control is-invalid" : "form-control"}
                                        name="productBaseNotes"
                                        value={productBaseNotes}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{productBaseNotesError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Price: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={priceError ? "form-control is-invalid" : "form-control"}
                                        name="price"
                                        value={price}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{priceError}</div>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label className="col-sm-4 col-form-label font-weight-bold">Image: </label>
                                <div className="col-sm-8">
                                    <input
                                        type="text"
                                        className={filenameError ? "form-control is-invalid" : "form-control"}
                                        name="filename"
                                        value={filename}
                                        onChange={handleInputChange}/>
                                    <div className="invalid-feedback">{filenameError}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-dark">
                        <FontAwesomeIcon className="mr-2" icon={faEdit}/>Edit
                    </button>
                </form>
            </div>
        </>
    );
};

export default EditProduct;

