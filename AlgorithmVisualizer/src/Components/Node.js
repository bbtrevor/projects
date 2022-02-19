const Node = (props) => {
    const pos = {
        position: 'absolute',
        height: props.node.radius * 2,
        width: props.node.radius * 2,
        top: props.node.y - props.node.radius,
        left: props.node.x - props.node.radius
    }
    let id = 'node-' + (props.node.id).toString()
    return (
        <div className='node created-element default-color' id={id} onClick={() => props.onClick(props.node)} style={pos}>
            
        </div>
    )


}

export default Node
