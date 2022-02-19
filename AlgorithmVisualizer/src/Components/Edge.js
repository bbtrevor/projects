
const Edge = (props) => {
    const edge = props.edge
    const thickness = 3
    const length = Math.sqrt((edge.endX - edge.startX)**2 + (edge.endY - edge.startY)**2)
    const centerX = ((edge.startX + edge.endX) / 2) - (length / 2)
    const centerY = ((edge.startY + edge.endY) / 2) - (thickness / 2)
    const angle = Math.atan2((edge.endY - edge.startY), (edge.endX - edge.startX)) * (180 / Math.PI)
    const style = {
        height: thickness,
        left: centerX,
        top: centerY,
        width: length,
        MozTransform: `rotate(${angle}deg)`,
        WebkitTransform: `rotate(${angle}deg)`,
        OTransform: `rotate(${angle}deg)`,
        msTransform: `rotate(${angle}deg)`,
        Transform: `rotate(${angle}deg)`,
    }    
    let id = 'edge-' + (edge.id).toString()

    return (
        <div className='edge created-element default-color' id={id} onClick={(e) => props.onClick(e, edge)} style={style}>

        </div>
    )
}

export default Edge
