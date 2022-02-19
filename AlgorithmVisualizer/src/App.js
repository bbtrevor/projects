import './master.css'
import { useState } from 'react'
import Header from './Components/Header'
import InteractiveBox from './Components/InteractiveBox';
import DropDownBox from './Components/DropDownBox';
import Graph from './Components/Graph';
import Popup from './Components/Popup';
import { Dijkstras } from './Algorithms/Dijkstras'
import tutorial_1 from './Tutorial/dijkstras_tutorial_1.PNG'
import tutorial_2 from './Tutorial/dijkstras_tutorial_2_resized.gif'
import tutorial_3 from './Tutorial/dijkstras_tutorial_3_resized.gif'
import tutorial_4 from './Tutorial/dijkstras_tutorial_4_resized.gif'
import tutorial_5 from './Tutorial/dijkstras_tutorial_5_resized.gif'
import tutorial_6 from './Tutorial/dijkstras_tutorial_6.PNG'
import tutorial_7 from './Tutorial/dijkstras_tutorial_7_resized.gif'
import tutorial_8 from './Tutorial/dijkstras_tutorial_8_resized.gif'
import 'bootstrap/dist/css/bootstrap.min.css';


function App() {

  const tutorial = [
    (
      <div>
        <h1>Dijkstra's Pathfinding Visualizer Tutorial</h1>
        <p style={{fontSize:'18px'}}>This tutorial will walk you through everything you need to know to use the features of this application.</p>
        <br/>
        <p>When you're ready, click "Get Started" to start the tutorial!</p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Get Started!</button>
      </div>
    ),
    (
      <div>
        <h1>How does Dijkstra's work?</h1>
        <p>Dijkstra's algorithm guarantees the shortest path from one node to another by finding the shortest path from 
          the starting node to every other node in the graph, and using that information to determine the shortest path 
          from the start node to the specified end node.
        </p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>
    ),
    (
      <div>
        <h1>Choosing an option</h1>
        <img src={tutorial_1} alt={'tut-1'}/>
        <p>
          To create a graph, you must first choose between creating it manually or having a random graph generated. To do this, select 
          the "Options" dropdown and choose which method of creating a graph you want.
        </p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>

    ),
    (
      <div>
        <h1>
          Manually creating a graph
        </h1>
        <h3>Creating nodes</h3>
        <img src={tutorial_2} alt={'tut-2'} />
        <p>
          To create a node, simply click within the graph area designated by the black border, as shown above.
        </p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>
    ),
    (
      <div>
        <h1>Manually creating a graph (cont.)</h1>
        <h3>Creating edges</h3>
        <img src={tutorial_3} alt={'tut-3'} />
        <p>
          To create an edge, simply click the first node of the edge followed by the second node of the edge, as shown above.
        </p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>
    ),
    (
      <div>
        <h1>Manually creating a graph (cont.)</h1>
        <h3>Deleting a node</h3>
        <img src={tutorial_4} alt={'tut-4'} />
        <p>To delete a node, click the node that you want to deleted, then click the "Delete selected node" button. 
          This will delete the node you selected as well as all of the edges connected to it.
        </p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>
    ),
    (
      <div>
        <h1>Manually creating a graph (cont.)</h1>
        <h3>Deleting an edge</h3>
        <img src={tutorial_5} alt={'tut-5'} />
        <p>To delete an edge, click the edge that you want to delete, then click the "Delete selected edge" button</p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>
    ),
    (
      <div>
        <h1>Creating a random graph</h1>
        <img src={tutorial_6} alt={'tut-6'} />
        <p>To create a random graph, simply click "Generate a random graph", and wait for the graph to be generated!</p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>
    ),
    (
      <div>
        <h1>Running the algorithm</h1>
        <img src={tutorial_7} alt={'tut-7'} />
        <p>Once you have created a graph, you may click the "Run Dijkstra's" button to start. After this, 
          you must first select the start node, followed by the end node. This will prompt the algorithm to begin and start 
          visualizing!
        </p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>
    ),
    (
      <div>
        <h1>Clearing a graph</h1>
        <img src={tutorial_8} alt={'tut-8'} />
        <p>You may clear a graph while creating it or after the algorithm has finished by simply clicking the "Clear Graph" button. 
        </p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
        <button className='btn tutorial-button-next' onClick={clickedNextTutorial}>Next</button>
      </div>
    ),
    (
      <div>
        <h1>That's it!</h1>
        <p>
          If you missed something, click
          <a style={{textDecoration:'none', color: 'red'}} onClick={runTutorial} href='#'>
            <b> here </b>
          </a> 
          to go through the tutorial again. Otherwise, click "Quit Tutorial", and Happy Visualizing!
        </p>
        <button className='btn tutorial-button-quit' onClick={onQuitTutorial}>Quit Tutorial</button>
      </div>
    )
  ]

  const welcomePopup = (
    <div>
      <h1>Welcome to Dijkstra's Pathfinding Visualizer!</h1>
      <p>
        This application provides several features to help visualize one of the most popular shortest pathfinding algorithms - Dijkstra's Algorithm. 
        To learn more about some of these features, feel free to go through the 
        <a style={{textDecoration:'none', color: 'red'}} onClick={runTutorial} href='#'>
          <b> tutorial</b>
        </a> for this application.
      </p>
      <br/>
      <p>Otherwise, click "Exit" to start visualizing!</p>
      <button className='btn' onClick={onQuitWelcome}>Exit</button>
    </div>
  )

  const [dropdownIsActive, setDropdownIsActive] = useState(false)
  const [createGraphManuallyEnabled, setCreateGraphManuallyEnabled] = useState(false);
  const [selectedOption, setSelectedOption] = useState('')
  const [nodes, setNodes] = useState([])
  const [edges, setEdges] = useState([])
  const [selectedNode, setSelectedNode] = useState(null)
  const [selectedEdge, setSelectedEdge] = useState(null)
  const [currentNodeId, setCurrentNodeId] = useState(0)
  const [currentEdgeId, setCurrentEdgeId] = useState(0)
  const [algorithmInProgress, setAlgorithmInProgress] = useState(false)
  const [startNode, setStartNode] = useState(null)
  const [endNode, setEndNode] = useState(null)
  const [algorithmJustFinished, setAlgorithmJustFinished] = useState(false)
  const [isGeneratingRandomGraph, setIsGeneratingRandomGraph] = useState(false)
  const [popupEnabled, setPopupEnabled] = useState(true)
  const [popupContent, setPopupContent] = useState(welcomePopup)

  const options = [
    '',
    'Create graph manually',
    'Create random graph' 
  ]

  let tutorialPageIndex = -1

  function runTutorial() {
    window.event.preventDefault()
    if (tutorialPageIndex == -1) {
      clickedNextTutorial()
    }
    else if (tutorialPageIndex == tutorial.length - 1) {
      tutorialPageIndex = 0
      setPopupContent(tutorial[tutorialPageIndex])
    }
    if (!popupEnabled) {
      setPopupEnabled(true)
      let optionsContent = document.getElementById('options-dropdown-content')
      let optionsButton = document.getElementById('options-button')
      optionsContent.style.display = ''
      optionsButton.style.border = 'none'
    }
  }

  function onQuitTutorial() {
    setPopupEnabled(false)
    tutorialPageIndex = -1
  }

  function onQuitWelcome() {
    setPopupEnabled(false)
  }
  
  function clickedNextTutorial() {
    if (tutorialPageIndex == tutorial.length - 1) {
      setPopupEnabled(false)
      tutorialPageIndex = -1
    }
    else {
      tutorialPageIndex++
      setPopupContent(tutorial[tutorialPageIndex])
    }
  }

  const GetSelectedOption = (e) => {
    window.event.preventDefault()
    let content = document.getElementById('options-dropdown-content')
    let button = document.getElementById('options-button')
    switch (e.target.innerHTML) {
      case options[0]:
        setCreateGraphManuallyEnabled(false)
        setDropdownIsActive(false)
        setSelectedOption(options[0])
        break
      case options[1]:
        if (nodes.length > 0) {
          clearGraph()
        }
        setCreateGraphManuallyEnabled(true)
        setDropdownIsActive(true)
        setSelectedOption(options[1])
        break
      case options[2]:
        if (nodes.length > 0) {
          clearGraph()
        }
        setCreateGraphManuallyEnabled(false)
        setDropdownIsActive(true)
        setSelectedOption(options[2])
        break
      default:
        break
    }
    content.style.display = ''
    button.style.border = 'none'
  }

  function pointIsOnNode(ptX, ptY, node) {
    var d = (ptX - node.x)**2 + (ptY - node.y)**2
    if (d <= node.radius**2) {
      return true
    }
    return false
  }

  function Node(x, y) {
    this.x = x;
    this.y = y;
    this.radius = 10;
    this.id = currentNodeId;
    this.edges = [];
    setCurrentNodeId(currentNodeId + 1)
    this.equals = function(node) {
      if (this.x == node.x && 
          this.y == node.y && 
          this.radius == node.radius) {
            return true
          }
        return false
    }
  }

  function Edge(node1, node2) {
    this.node1 = node1;
    this.node2 = node2;
    this.startX = node1.x;
    this.startY = node1.y;
    this.endX = node2.x;
    this.endY = node2.y;
    this.weight = 1;
    this.id = currentEdgeId;
    setCurrentEdgeId(currentEdgeId + 1)
    this.equals = function(edge) {
      if (this.node1.equals(edge.node1) && 
          this.node2.equals(edge.node2)) {
            return true
          }
        return false
    }
  }

  function offset(e) {
    var bound = e.getBoundingClientRect();
    var html = document.documentElement;
    return {
      left: bound.left + window.pageXOffset - html.clientLeft,
      top: bound.top + window.pageYOffset - html.clientTop
    }
  }

  function clearGraph() {
    if (popupEnabled) {
      return
    }
    if (algorithmInProgress) {
      return
    }
    setAlgorithmJustFinished(false)
    for (let i = 0; i < edges.length; i++) {
      let edgeElement = document.getElementById('edge-' + (edges[i].id).toString())
      edgeElement.classList.remove('created-element')
      edgeElement.classList.add('deleted-element') 
    }
    for (let i = 0; i < nodes.length; i++) {
      let nodeElement = document.getElementById('node-' + (nodes[i].id).toString())
      nodeElement.classList.remove('created-element')
      nodeElement.classList.add('deleted-element') 
    }
    setTimeout(() => {
      setSelectedEdge(null)
      setSelectedNode(null)
      setEdges([])
      setNodes([])
    }, 301)
  }

  function getRandomInt(x) {
    return Math.floor(Math.random() * x)
  }

  function isConnected(nodes) {
    if (nodes.length == 0) {
      return false
    }
    let visited = new Array(nodes.length)
    depthFirstSearch(nodes[0], nodes, visited)

    let connected = true
    for (let i = 0; i < visited.length; i++) {
      if (!visited[i]) {
        connected = false
        break
      }
    }
    return connected
  }

  function depthFirstSearch(start, nodes, visited) {
    visited[nodes.indexOf(start)] = true
    for (let i = 0; i < start.edges.length; i++) {
      let neighbor = start.equals(start.edges[i].node1) ? start.edges[i].node2 : 
        start.edges[i].node1
      if (!visited[nodes.indexOf(neighbor)]) {
        depthFirstSearch(neighbor, nodes, visited)
      }
    }
  }

  function disablePopup() {
    setPopupEnabled(false)
  }

  function runAlgorithm() {
    if (popupEnabled) {
      return
    }
    else if (nodes.length < 2) {
      let nodeWord = nodes.length == 0 ? 'nodes' : 'node'
      let content = (
        <div className='popup-notice'>
          <h1>Too few nodes.</h1>
          <p>To run Dijkstra's algorithm, there must be atleast two nodes in the graph. 
          Create at least {2 - nodes.length} more {nodeWord} to visualize this algorithm.</p>
          <button className=' btn popup-exit' onClick={disablePopup}>Got it!</button>
        </div>
      )
      setPopupContent(content)
      setPopupEnabled(true)
      return
    }
    else if (!isConnected(nodes)) {
      let content = (
        <div style={{height: '400px'}} className='popup-notice'>
          <h1>Graph not connected.</h1>
          <p>To run Dijkstra's algorithm, there must be a path from each node to every other node in the graph using edges.
          Connect the graph to visualize this algorithm.</p>
          <button className='btn popup-exit' onClick={disablePopup}>Got it!</button>
        </div>
      )
      setPopupContent(content)
      setPopupEnabled(true)
      return
    }
    let optionsContent = document.getElementById('options-dropdown-content')
    let optionsButton = document.getElementById('options-button')
    optionsContent.style.display = ''
    optionsButton.style.border = 'none'

    nodes.forEach((n) => {
      let nodeElement = document.getElementById('node-' + (n.id).toString())
      if (nodeElement.classList.contains('selected-element')) {
        nodeElement.classList.remove('selected-element')
      }
    })
    edges.forEach((e) => {
      let edgeElement = document.getElementById('edge-' + (e.id).toString())
      if (edgeElement.classList.contains('selected-element')) {
        edgeElement.classList.remove('selected-element')
      }
    })
    let content = (
      <div>
        <h1>Running Dijkstra's algorithm!</h1>
        <p>To have Dijkstra's visualized, please select the starting node followed by the desired end node.</p>
        <button className='btn popup-exit' onClick={disablePopup}>Got it!</button>
      </div>
    )
    setPopupContent(content)
    setPopupEnabled(true)
    setAlgorithmInProgress(true)

  }

  function runDijkstras(start, end) {

    nodes.forEach((n) => {
      let nodeElement = document.getElementById('node-' + (n.id).toString())
      if (nodeElement.classList.contains('selected-element')) {
        nodeElement.classList.remove('selected-element')
      }
    })
    
    edges.forEach((e) => {
      let edgeElement = document.getElementById('edge-' + (e.id).toString())
      if (edgeElement.classList.contains('selected-element')) {
        edgeElement.classList.remove('selected-element')
      }
    })
    setTimeout(() => {
      Dijkstras(nodes, start, end, setAlgorithmInProgress)
    }, 400)
  }

  function createRandomGraph() {
    if (popupEnabled) {
      return
    }
    setIsGeneratingRandomGraph(true)
    let timeoutTime = 0
    let intervalTime = 125
    let currentNodeIdForRandomGraph = currentNodeId
    let currentEdgeIdForRandomGraph = currentEdgeId
    if (nodes.length > 0 || edges.length > 0) {
      clearGraph()
      timeoutTime = 100
    }
    setTimeout(() => {
      let graphElement = document.getElementById('graph')
      let boundingRect = graphElement.getBoundingClientRect()
      let o = offset(graphElement)
      let graph = graphElement.getBoundingClientRect()
      let y = boundingRect.y - o.top + 15,
          x = boundingRect.x - o.left + 40,
          height = graph.height,
          width = graph.width,
          yFieldDifference = 400,
          xFieldDifference = 260,
          yBound = y + height - yFieldDifference,
          xBound = x + width - xFieldDifference
          
      let xClone = x
      let nodesToSpawn = getRandomInt(3) + 2
      let nodesSpawned = 0
      let randomNodes = []
      let randomEdges = []
      let intervalID = setInterval(() => {
        if (y >= yBound) {

            if (isConnected(randomNodes)) {
              setIsGeneratingRandomGraph(false)
              clearInterval(intervalID)
              return
            }
            let num1 = getRandomInt(randomNodes.length)
            let num2 = getRandomInt(randomNodes.length)
            while (num1 === num2) {
              num2 = getRandomInt(randomNodes.length)
            }
            let n = 0
            console.log(randomEdges)
            while (n < randomEdges.length) {
              console.log(n)
              if ((randomEdges[n].node1.equals(randomNodes[num1]) && 
                randomEdges[n].node2.equals(randomNodes[num2])) || 
                (randomEdges[n].node1.equals(randomNodes[num2]) && 
                randomEdges[n].node2.equals(randomNodes[num1]))) {
                  console.log('eee')
                  num2 = getRandomInt(randomNodes.length)
                  while (num1 !== num2) {
                    num1 = getRandomInt(randomNodes.length)
                    num2 = getRandomInt(randomNodes.length)
                  }
                  n = 0
                  continue
                }
                n++
            }
            
            let edge = new Edge(randomNodes[num1], randomNodes[num2])
            randomNodes[num1].edges.push(edge)
            randomNodes[num2].edges.push(edge)
            edge.id = currentEdgeIdForRandomGraph
            currentEdgeIdForRandomGraph++
            setCurrentEdgeId(currentEdgeId + 1)
            randomEdges.push(edge)
            setEdges(edges.concat(randomEdges).filter((e => edges.indexOf(e) < 0)))
            return            
        }
        else {
          let thisX = x + 10 + getRandomInt(xFieldDifference / 2)
          let thisY = y + 10 + getRandomInt(yFieldDifference / 2)
          let node = new Node(thisX, thisY)
          node.id = currentNodeIdForRandomGraph
          currentNodeIdForRandomGraph++
          setCurrentNodeId(currentNodeId + 1)
          randomNodes.push(node)
          setNodes(nodes.concat(randomNodes).filter((n => nodes.indexOf(n) < 0)))
          if (randomNodes.length > 0 || nodesSpawned >= nodesToSpawn) {
            randomNodes.pop()
          }
          randomNodes.push(node)
          if (x >= xBound - xFieldDifference) {
            x = xClone
            y += yFieldDifference
            nodesSpawned = 0
          }
          else {
            x += xFieldDifference
            nodesSpawned++
          }
        }
      }
      , intervalTime)
    }, timeoutTime)
  }

  function spawnNode(e) {
    if (!createGraphManuallyEnabled || algorithmInProgress || algorithmJustFinished) {
      return
    }
    if (e.target.classList.contains('edge')) {

      return
    }
    var graph = document.getElementById('graph')
    var o = offset(graph)
    var x = e.pageX - o.left
    var y = e.pageY - o.top
    let isValid = true
    nodes.forEach((node) => {
      if (pointIsOnNode(x, y, node)) {
        isValid = false
        return
      }
    })
    if (!isValid) {
      return
    }
    var newNode = new Node(x, y)
    setNodes([...nodes, newNode])
  }

  function spawnEdge(node1, node2) {
    for (let i = 0; i < edges.length; i++) {
      if ((edges[i].node1.equals(node1) && edges[i].node2.equals(node2)) || 
        (edges[i].node1.equals(node2) && edges[i].node2.equals(node1))) {
          return
        }
    }
    var newEdge = new Edge(node1, node2)
    newEdge.node1.edges.push(newEdge)
    newEdge.node2.edges.push(newEdge)
    setEdges([...edges, newEdge])
  }

  function nodeOnClick(node) {
    if (algorithmJustFinished) {
      if (algorithmInProgress) {
        setAlgorithmJustFinished(false)
      }
      else {
        return
      }
    }
    if (algorithmInProgress) {
      if (startNode && endNode) {
        return
      }
      document.getElementById('node-' + (node.id).toString()).classList.add('selected-element')
      if (startNode == null) {
        setStartNode(node)
        return
      }
      else if (endNode == null) {
        if (!node.equals(startNode)) {
          setEndNode(node)
          setTimeout(() => {
            document.getElementById('node-' + (node.id).toString()).classList.remove('selected-element')
            document.getElementById('node-' + (startNode.id).toString()).classList.remove('selected-element')
            setEndNode(node)
            runDijkstras(startNode, node)
            setAlgorithmJustFinished(true)
            setStartNode(null)
            setEndNode(null)
          }, 800)
          return
        }
        else {
          return
        }
      }
    }
    if (selectedOption === options[0] || 
        selectedOption === options[2]) {
      return
    }
    if (selectedNode == null) {
      let n = document.getElementById('node-' + (node.id).toString())
      n.classList.add('selected-element')
      setSelectedNode(node)
    }
    else {
      if (selectedNode.equals(node)) {
        return
      }
      let n = document.getElementById('node-' + (selectedNode.id).toString())
      n.classList.remove('selected-element')
      spawnEdge(selectedNode, node)
      setSelectedNode(null)
    }
  }
  
  function nodeOnDelete() {
    if (selectedNode == null || popupEnabled) {
      return
    }
    let nodeToDelete = selectedNode
    let nodeElement = document.getElementById('node-' + (nodeToDelete.id).toString())
    if (nodeToDelete.edges.length > 0) {
      for (let i = 0; i < nodeToDelete.edges.length; i++) {
        let edgeElement = document.getElementById('edge-' + (nodeToDelete.edges[i].id).toString())
        if (edgeElement == null) {
          continue
        }
        edgeElement.classList.remove('created-element')
        edgeElement.classList.add('deleted-element')
      }
    }
    nodeElement.classList.remove('created-element')
    nodeElement.classList.add('deleted-element')
    setTimeout(() => {
      setSelectedNode(null)
      setNodes(nodes.filter(node => node.id !== nodeToDelete.id))
      let idsToDelete = []
      if (nodeToDelete.edges.length > 0) {
        for (let x = 0; x < nodeToDelete.edges.length; x++) {
          idsToDelete.push(nodeToDelete.edges[x].id)
          let nodeEdges = nodeToDelete.edges[x].node1.equals(nodeToDelete) ? nodeToDelete.edges[x].node2.edges : 
            nodeToDelete.edges[x].node1.edges
          for (let j = 0; j < nodeEdges.length; j++) {
            if (nodeEdges[j].equals(nodeToDelete.edges[x])) {
              nodeEdges.splice(j, 1)
              break
            }
          }
        }
        setEdges(edges.filter(edge => !idsToDelete.includes(edge.id)))
      }
    }, 301)
  }

  function appOnClick(e) {
    if (algorithmInProgress) {
      return
    }
    if (selectedNode != null) {
      var graph = document.getElementById('graph')
      var o = offset(graph)
      var x = e.pageX - o.left
      var y = e.pageY - o.right
      if (pointIsOnNode(x, y, selectedNode)) {
        return
      }
      let n = document.getElementById('node-' + (selectedNode.id).toString())
      n.classList.remove('selected-element')
      setSelectedNode(null)
    }
    if (selectedEdge != null) {
      let e = document.getElementById('edge-' + (selectedEdge.id).toString())
      e.classList.remove('selected-element')
      setSelectedEdge(null)
    }
  }

  async function edgeOnClick(e, edge) {
    if (selectedOption === options[2] || 
        selectedOption === options[0] || 
        algorithmInProgress || 
        algorithmJustFinished) {
      return
    }
    
    setTimeout(() => {
      setSelectedEdge(edge)
      let e = document.getElementById('edge-' + (edge.id).toString())
      e.classList.add('selected-element')
    }, 2)
  }

  function edgeOnDelete() {
    if (selectedEdge == null || popupEnabled) {
      return
    }
    for (let i = 0; i < selectedEdge.node1.edges.length; i++) {
      if (selectedEdge.node1.edges[i].equals(selectedEdge)) {
        selectedEdge.node1.edges.splice(i, 1)
        break
      }
    }
    for (let i = 0; i < selectedEdge.node2.edges.length; i++) {
      if (selectedEdge.node2.edges[i].equals(selectedEdge)) {
        selectedEdge.node2.edges.splice(i, 1)
        break
      }
    }
    let edgeElement = document.getElementById('edge-' + (selectedEdge.id).toString())
    edgeElement.classList.remove('created-element')
    edgeElement.classList.add('deleted-element')
    setTimeout(() => {
      setEdges(edges.filter(edge => edge.id !== selectedEdge.id))
      setSelectedEdge(null)
    }, 301)
  }

  function clickedOptions() {
    if (algorithmInProgress || popupEnabled) {
      return
    }
    window.event.preventDefault()
    let content = document.getElementById('options-dropdown-content')
    let button = document.getElementById('options-button')
    if (content.style.display === '') {
      content.style.display = 'block'
      button.style.border = '1px solid lightblue'
      button.style.borderRadius = '8px'
    }
    else {
      content.style.display = ''
      button.style.border = 'none'
      button.style.borderRadius = '0'
    }
  }

  return (
    <div className="App" onClick={(e) => appOnClick(e)}>
      <Popup content={popupContent} enabled={popupEnabled} />
      <Header/>
      <InteractiveBox options={options} getSelectedOption={GetSelectedOption} clickedOptions={clickedOptions} 
        runTutorial={runTutorial} />
      <DropDownBox isActive={dropdownIsActive} selectedOption={selectedOption} nodeOnDelete={nodeOnDelete} 
        selectedNode={selectedNode} selectedEdge={selectedEdge} edgeOnDelete={edgeOnDelete} clearGraph={clearGraph} 
        createRandomGraph={createRandomGraph} runAlgorithm={runAlgorithm} nodes={nodes}
        algorithmInProgress={algorithmInProgress} isGeneratingRandomGraph={isGeneratingRandomGraph} />
      <Graph nodes={nodes} onClick={spawnNode} nodeOnClick={(node) => nodeOnClick(node)} 
      nodeOnDelete={nodeOnDelete} edges={edges} edgeOnClick={edgeOnClick} edgeOnDelete={edgeOnDelete} />
    </div>
  );
}

export default App;
