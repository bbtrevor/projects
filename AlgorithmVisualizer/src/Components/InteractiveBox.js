const InteractiveBox = (props) => {


    return (
        <div className='interactive-box'>
            <div id='options-dropdown'>
                <button id='options-button' onClick={props.clickedOptions}>
                    Options <span className='dropdown-arrow'>▼</span>
                </button>
                    <div id='options-dropdown-content'>
                        {props.options.map((option, i) => 
                            <a className='options' href='#' onClick={(e) => props.getSelectedOption(e)} key={i}>
                                {option}
                            </a>
                        )}
                    </div>
            </div>
            <button id='tutorial-button' onClick={props.runTutorial} >Tutorial</button>
        </div>
    )
}

export default InteractiveBox
