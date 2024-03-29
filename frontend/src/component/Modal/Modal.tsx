import React, {FC} from 'react';
import {Product} from "../../types/types";
import {LazyLoadImage} from "react-lazy-load-image-component";

type PropTypes = {
    product?: Product
    deleteProductHandler: (id?: number) => void
    setModalActive: React.Dispatch<React.SetStateAction<boolean>>
};

const Modal: FC<PropTypes> = ({product,deleteProductHandler, setModalActive}) => {
    return (
        <>
            <div className="modal-open">
                <div className="modal fade show" style={{display: "block"}}>
                    <div className="modal-dialog modal-dialog-centered" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title">Delete toy</h5>
                                <button type="button" className="close" onClick={() => setModalActive(false)}>
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="row modal-body">
                                <div className="col-md-6 d-flex justify-content-center">
                                    <LazyLoadImage
                                        effect="blur"
                                        style={{width: "89px"}}
                                        src={product?.filename}/>
                                </div>
                                <div className="col-md-6 text-center">
                                    <p> Are you sure too delete?</p>
                                    <h6>{product?.producer}</h6>
                                    <h6>{product?.title}</h6>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button"
                                        className="btn btn-danger"
                                        onClick={() => deleteProductHandler(product?.id)}>Delete
                                </button>
                                <button type="button"
                                        className="btn btn-secondary"
                                        data-dismiss="modal"
                                        onClick={() => setModalActive(false)}>Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="modal-backdrop fade show"></div>
        </>
    );
};

export default Modal;
