import React, {FC, FormEvent, useEffect, useState} from 'react';
import {Route, RouteComponentProps, useHistory} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCartPlus, faPaperPlane, faStar} from "@fortawesome/free-solid-svg-icons";
import {CompatClient, Stomp} from '@stomp/stompjs';
import StarRatingComponent from 'react-star-rating-component';

import {fetchProduct} from "../../redux/thunks/product-thunks";
import {addReviewToProduct, resetForm} from "../../redux/thunks/user-thunks";
import {AppStateType} from "../../redux/reducers/root-reducer";
import {Product, Review, ReviewData, ReviewError} from "../../types/types";
import halfStar from "../../img/star-half.svg";
import Spinner from "../../component/Spinner/Spinner";
import ProductReview from "./ProductReview";
import ScrollButton from "../../component/ScrollButton/ScrollButton";

let stompClient: CompatClient | null = null;

const ProductPage: FC<RouteComponentProps<{ id: string }>> = ({match}) => {
    const dispatch = useDispatch();
    const history = useHistory();
    const product: Partial<Product> = useSelector((state: AppStateType) => state.product.product);
    const reviews: Array<Review> = useSelector((state: AppStateType) => state.product.reviews);
    const errors: Partial<ReviewError> = useSelector((state: AppStateType) => state.user.reviewErrors);
    const isReviewAdded: boolean = useSelector((state: AppStateType) => state.user.isReviewAdded);
    const loading: boolean = useSelector((state: AppStateType) => state.product.isProductLoading);

    const [author, setAuthor] = useState<string>("");
    const [message, setMessage] = useState<string>("");
    const [rating, setRating] = useState<number>(0);
    const {authorError, messageError, ratingError} = errors;

    useEffect(() => {
        dispatch(fetchProduct(match.params.id));
        dispatch(resetForm());
        window.scrollTo(0, 0);
    }, []);

    useEffect(() => {
        setAuthor("");
        setMessage("");
        setRating(0);
    }, [isReviewAdded]);

    const addToCart = (): void => {
        const productId: number | undefined = product.id;
        let data: string | null = localStorage.getItem("products");
        let cart: Map<number, any> = data ? new Map(JSON.parse(data as string)) : new Map();

        if (cart.has(productId as number)) {
            cart.set(productId as number, cart.get(productId as number) + 1);
        } else {
            cart.set(productId as number, 1);
        }
        localStorage.setItem("products", JSON.stringify(Array.from(cart.entries())));
        history.push("/cart");
    };

    const addReview = (event: FormEvent<HTMLFormElement>): void => {
        event.preventDefault();
        const review: ReviewData = {productId: match.params.id as string, author, message, rating}
        dispatch(addReviewToProduct(review));
    };

    const renderStars = (productRating: number = 5): JSX.Element => {
        return (
            <StarRatingComponent
                renderStarIconHalf={() => <img src={halfStar} alt="halfStar"
                                               style={{width: "14.5px", marginBottom: "2px"}}/>}
                renderStarIcon={() => <FontAwesomeIcon className="fa-sm" icon={faStar}/>}
                name={"star"}
                starCount={5}
                editing={false}
                value={productRating}/>
        );
    };

    return (
        <div className="container mt-5 pb-5">
            {loading ? <Spinner/> : <>
                <ScrollButton/>
                <div className="row">
                    <div className="col-md-5">
                        <div>
                            <img src={product.filename} className="rounded mx-auto w-100"/>
                        </div>
                    </div>
                    <div className="col-md-7">
                        <h2>{product.title}</h2>
                        <h3>{product.producer}</h3>
                        <p>Product code: <span>{product.id}</span></p>
                        <div className="row">
                            <div className="col-md-2">
                                {renderStars(product.productRating === 0 ? 5 : product.productRating)}
                            </div>
                            <div className="col-md-10">
                                <span style={{paddingBottom: "50px"}}>{product.reviews?.length} reviews</span>
                            </div>
                        </div>
                        <p style={{color: "#54C0A1"}}>In Stock</p>
                        <div className="row ml-1">
                            <h6 className="mr-5"><span>${product.price}</span>.00</h6>
                            <button type="submit"
                                    className="btn btn-success mx-3"
                                    onClick={addToCart}>
                                <FontAwesomeIcon className="mr-2 fa-lg" icon={faCartPlus}/> ADD TO CART
                            </button>
                        </div>
                        <br/>
                        <table className="table">
                            <tbody>
                            <tr>
                                <td>Toy title:</td>
                                <td>{product.title}</td>
                            </tr>
                            <tr>
                                <td>Manufacturer:</td>
                                <td>{product.producer}</td>
                            </tr>
                            <tr>
                                <td>Toy type:</td>
                                <td>{product.type}</td>
                            </tr>
                            <tr>
                                <td>Country:</td>
                                <td>{product.country}</td>
                            </tr>
                            <tr>
                                <td>Gender:</td>
                                <td>{product.productGender}</td>
                            </tr>
                            <tr>
                                <td>Top notes:</td>
                                <td>{product.productTopNotes}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div className="mt-5">
                    <h3 className="text-center mb-5">REVIEWS</h3>
                    <Route exact component={() => <ProductReview data={reviews} itemsPerPage={5}/>}/>
                    <form onSubmit={addReview}>
                        <div className="form-group border mt-5">
                            <div className="mx-3 my-3">
                                <div className="row">
                                    <div className="col-md-4">
                                        <label><span className="text-danger"><b>*</b></span> Your name</label>
                                        <input
                                            type="text"
                                            className={authorError ? "form-control is-invalid" : "form-control"}
                                            name="author"
                                            value={author}
                                            onChange={(event) => setAuthor(event.target.value)}/>
                                        <div className="invalid-feedback">{authorError}</div>
                                        <label><span className="text-danger"><b>*</b></span> Message text</label>
                                    </div>
                                    <div className="col-md-8">
                                        <label><span className="text-danger"><b>*</b></span> Your mark</label>
                                        <div>
                                            <StarRatingComponent
                                                name="star"
                                                starCount={5}
                                                value={rating}
                                                onStarClick={(value) => setRating(value)}
                                                renderStarIcon={() => <FontAwesomeIcon className="fa-sm"
                                                                                       icon={faStar}/>}/>
                                            <div className="invalid-feedback d-block">{ratingError}</div>
                                        </div>
                                    </div>
                                </div>
                                <textarea
                                    rows={4}
                                    className={messageError ? "form-control is-invalid" : "form-control"}
                                    name="message"
                                    value={message}
                                    style={{resize: "none"}}
                                    onChange={(event) => setMessage(event.target.value)}/>
                                <div className="invalid-feedback">{messageError}</div>
                                <button type="submit" className="btn btn-dark mt-3">
                                    <FontAwesomeIcon className="mr-2" icon={faPaperPlane}/>Post a review
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </>}
        </div>
    );
};

export default ProductPage;
