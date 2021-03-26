import React from 'react';
import $ from 'jquery';
import 'bootstrap-daterangepicker';
import getOptions from './getOptions';

const events = ['Show', 'Hide', 'ShowCalendar', 'HideCalendar', 'Apply', 'Cancel'];

class DatetimeRangePicker extends React.Component {

  constructor(props) {
    super(props);
    this.state = {};

    this.$picker = undefined;
    this.picker = undefined;
    this.options = getOptions();

    this.handleCallback = this.handleCallback.bind(this);
  }

  componentDidMount() {
    this.$picker = $(this.refs.picker);
    this.$picker.daterangepicker(this.getOptionsFromProps(), this.handleCallback);
    events.forEach(event => {
      const lcase = event.toLowerCase();
      this.$picker.on(`${lcase}.daterangepicker`, this.makeEventHandler(`on${event}`));
    });
  }

  componentWillUnmount() {
    this.getPicker().remove();
  }

  setOptionsFromProps() {
    const currentOptions = this.getOptionsFromProps();
    const keys = Object.keys(currentOptions);
    if (this.$picker) {
      if (currentOptions) {
        keys.forEach(key => {
          this.applyOptionToPicker(key, currentOptions[key]);
        });
      }
    }
  }

  getPicker() {
    return this.$picker && this.$picker.data('daterangepicker');
  }

  getOptionsFromProps() {
    const options = {};
    const { props } = this;
    let value;
    this.options.forEach(name => {
      if (props.hasOwnProperty(name)) {
        value = props[name];

        switch (name) {
          case 'startDate':
          case 'endDate':
            if (value) {
              options[name] = value;
            }
            break;

          case 'locale':
            if (value && typeof value === 'object') {
              const picker = this.getPicker();
              if (picker) {
                value = $.extend({}, value, picker.locale);
              }
            }
            options[name] = value;
            break;

          default:
            options[name] = value;
        }
      }
    });
    return options;
  }

  applyOptionToPicker(key, value) {
    if (this.$picker) {
      this.$picker.data('daterangepicker')[key] = value;
    }
  }

  handleCallback(start, end) {
    if (typeof this.props.callback === 'function') {
      this.props.callback(start, end);
    }
  }

  makeEventHandler(eventType) {
    return (event, picker) => {
      if (this.props.onEvent) {
        this.props.onEvent(event, picker);
      }
      if (typeof this.props[eventType] === 'function') {
        this.props[eventType](event, picker);
      }
    };
  }

  render() {
    const { style, className } = this.props;
    this.setOptionsFromProps();

    return (
      <div ref="picker" style={style} className={className}>
        {this.props.children}
      </div>
    );
  }
}

export default DatetimeRangePicker;
