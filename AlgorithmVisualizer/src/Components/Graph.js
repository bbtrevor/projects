import Node from './Node'
import PropTypes from 'prop-types'
import Edge from './Edge'

const Graph = (props) => {
    
    return (
        <div className='graph-container'>
            <div id='graph' onClick={(e) => props.onClick(e)}>
    

                    {props.nodes.map((node) => 
                    <Node key={node.id} id={node.id} node={node} onClick={props.nodeOnClick} onDelete={props.nodeOnDelete}/>)}
                    {props.edges.map((edge, i) => 
                    <Edge key={edge.id} id = {edge.id} edge={edge} onClick={props.edgeOnClick} onDelete={props.edgeOnDelete}/>)}
            </div>
        </div>
    )
}

Graph.propTypes = {
    
}

export default Graph
