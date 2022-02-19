const DropDownBox = (props) => {

    var active = props.isActive ? ' drop-down-active' : ''
    var content = ''
    if (props.selectedOption === 'Create graph manually') {
        content = (
            <div className='drop-down-box-text'>
                <button className='btn btn-light run-algorithm' disabled={props.algorithmInProgress} 
                    onClick={props.runAlgorithm}>Run Dijkstra's</button>
                <button className='btn btn-light clear-graph' onClick={props.clearGraph} >Clear graph</button>
                <button className='btn btn-light delete-node' disabled={props.selectedNode == null} 
                    onClick={props.nodeOnDelete}>Delete selected node</button>
                <button className='btn btn-light delete-edge' disabled={props.selectedEdge == null} 
                    onClick={props.edgeOnDelete}>Deleted selected edge</button>
            </div>
        )
    }
    else if (props.selectedOption === 'Create random graph') {
        content = (
            <div className='drop-down-box-text'>
                <button className='btn btn-light run-algorithm' disabled={props.algorithmInProgress} 
                    onClick={props.runAlgorithm}>Run Dijkstra's</button>
                <button className='btn btn-light' style={{marginRight: '15px'}} disabled={props.isGeneratingRandomGraph} onClick={props.createRandomGraph}>Generate a random graph</button>
                <button className='btn btn-light clear-graph' onClick={props.clearGraph}>Clear graph</button>
            </div>
        )
    }
    else {
        content = ''
    }
    return (
        
        <div className={'drop-down-box' + active}>
            {content}
        </div>
    )
}

export default DropDownBox
