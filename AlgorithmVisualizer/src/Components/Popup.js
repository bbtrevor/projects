import React from 'react'

const Popup = (props) => {
    return (
        <div id='popup' style={{display: props.enabled ? 'block' : 'none'}}>
            {props.content}
        </div>
    )
}

export default Popup
