import React, {FC} from 'react';

import "./Footer.css";

const Footer: FC = () => {
    return (
        <footer className="page-footer p-5 bg-lightBrown text-white">
            <div className="container">
                <div className="d-flex justify-content-between">
                    <div className="footer-left">
                        <h3>Koala Kids Toy Store</h3>
                        <p className="text-patelBrown">(+398) 78 631 022</p>
                        <br/>
                        <p className="text-patelBrown">from 08:00 to 22:00 without breaks, weekends and holidays</p>
                    </div>
                    <div className="footer-right" style={{color: "#7E685A"}}>
                        <h3>Social networks</h3>
                        <a href="https://www.linkedin.com/">
                            <i className="fab fa-linkedin fa-2x mr-3" style={{color: "lavenderblush"}}></i>
                        </a>
                        <a href="#"><i className="fab fa-facebook-f fa-2x mr-3" style={{color: "lavenderblush"}}></i></a>
                        <a href="#"><i className="fab fa-twitter fa-2x mr-3" style={{color: "lavenderblush"}}></i></a>
                    </div>
                </div>
                <div className="mx-auto" style={{width: "200px", color: "#7E685A"}}>
                    <p>North Macedonia</p>
                </div>
            </div>
        </footer>
    );
}

export default Footer
