import {NativeModules} from 'react-native';

import React, {Component, PropTypes} from 'React';
import NativeMethodsMixin from 'react/lib/NativeMethodsMixin';
import ReactPropTypes from 'react/lib/ReactPropTypes';
import View from 'View';
import ColorPropType from 'ColorPropType';
var STYLE_ATTRIBUTES = [
    'Normal'
];
import requireNativeComponent from 'requireNativeComponent';
class SeekBar extends Component {
    static propTypes = {
        onChange: ReactPropTypes.func,
        onTrackingTouch: ReactPropTypes.func,
        color: ColorPropType,
        secondaryColor: ColorPropType,
        bgColor: ColorPropType,
        thumbColor: ColorPropType,
        progress: ReactPropTypes.number,
        secondaryProgress: ReactPropTypes.number,
        max: ReactPropTypes.number,
        styleAttr: ReactPropTypes.oneOf(STYLE_ATTRIBUTES),
        scaleX: PropTypes.number,
        scaleY: PropTypes.number,
        translateX: PropTypes.number,
        translateY: PropTypes.number,
        rotation: PropTypes.number,
        ...View.propTypes
    }
    static defaultProps = {
        max: 100,
        styleAttr: 'Normal'
    };
    _assignRoot = (component) => {
        this._root = component;
    }
    _onChange = (event) => {
        if (this.props.onChange) {
            this.props.onChange(event.nativeEvent);
        }
    }
    _onTrackingTouch = (event) => {
        if (this.props.onTrackingTouch) {
            this.props.onTrackingTouch(event.nativeEvent.pressed);
        }
    }
    render() {
        const nativeProps = Object.assign(SeekBar.defaultProps, this.props);
        return <AndroidSeekBar ref={this._assignRoot} {...nativeProps} onTrackingTouch={this._onTrackingTouch} onChange={this._onChange}/>
    }
}
var AndroidSeekBar = requireNativeComponent('RNSeekBar', SeekBar, {
    nativeOnly: {
        onChange: true
    }
});
export default SeekBar;
