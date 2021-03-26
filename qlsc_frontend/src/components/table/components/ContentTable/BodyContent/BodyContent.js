import React from 'react';
import ContentHeader from "./ContentHeader";
import ContentBody from "./ContentBody";
import ContentFooter from "./ContentFooter";
import "./bodyContent.scss";

function BodyContent(props) {
    return (
        <div className="dashboard-body-content">
            <div className="content-container">
                <div className="list-wrapper">
                    <ContentHeader />
                    <ContentBody />
                    <ContentFooter />
                </div>
            </div>
        </div>
    );
}

export default BodyContent;