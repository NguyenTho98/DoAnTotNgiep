import React from 'react';

function Phone(props) {
  return (
    <span className="filter-detail">
            <span className="active-status">
              {this.state.filterCommentPhone.commentHasPhone && this.state.filterCommentPhone.status ? (
                <button className="btn-active" onClick={() => this.filterAction(option, true)} style={{ background: '#4e95e3', color: 'white' }} type="button">
                  Có số điện thoại
                </button>
              ) : (
                <button className="btn-active" onClick={() => this.filterAction(option, true)} type="button">
                  Có số điện thoại
                </button>
              )}
            </span>
            <span className="active-status">
              {!this.state.filterCommentPhone.commentHasPhone && this.state.filterCommentPhone.status ? (
                <button className="btn-unactive" onClick={() => this.filterAction(option, false)} style={{ background: '#4e95e3', color: 'white' }} type="button">
                  Không có số điện thoại
                </button>
              ) : (
                <button className="btn-unactive" onClick={() => this.filterAction(option, false)} type="button">
                  Không có số điện thoại
                </button>
              )}
            </span>
      </span>
  );
}

export default Phone;
