
function showPath(distances, endIndex, setAlgorithmInProgress) {
    let m = 0
    let intervalID = setInterval(() => {
        if (m >= distances[endIndex].nodes.length) {
            distances[endIndex].edges.forEach((e) => {
                document.getElementById('edge-' + (e.id).toString()).classList.add('selected-element')
            })
            setAlgorithmInProgress(false)
            clearInterval(intervalID)
            return
        }
        document.getElementById('node-' + (distances[endIndex].nodes[m].id).toString()).classList.add('selected-element')
        m++
    }, 375)
}

export function Dijkstras(nodes, start, end, setAlgorithmInProgress) {
    var distances = new Array(nodes.length);
    let startIndex = nodes.indexOf(start)
    let endIndex = nodes.indexOf(end)
    let paths = new Array(nodes.length)

    var visited = new Array(nodes.length);
    for (let i = 0; i < nodes.length; i++) {
        distances[i] = {
            currDis: Infinity,
            edges: [],
            nodes: []
        }
        visited[i] = false
        paths[i] = new Array(nodes.length)
    }
    distances[startIndex].currDis = 0;

    let selectedElements = []
    while (true) {
        var shortestDistance = Infinity;
        var shortestIndex = -1;
        for (let i = 0; i < nodes.length; i++) {
            if (distances[i].currDis < shortestDistance && !visited[i]) {
                shortestDistance = distances[i].currDis;
                shortestIndex = i;
            }
        }
        if (shortestIndex === -1) {
            for (let n = 0; n < distances.length; n++) {
                distances[n].nodes.push(nodes[n])
            }
            break;
        }
        let currNode = nodes[shortestIndex]
        let elements = []
        let n = document.getElementById('node-' + (currNode.id).toString())
        elements.push(n)
        for (var i = 0; i < currNode.edges.length; i++) {
            let x = currNode.edges[i].node1.equals(currNode) ? nodes.indexOf(currNode.edges[i].node2) : 
                nodes.indexOf(currNode.edges[i].node1)
            if (distances[x].currDis > distances[shortestIndex].currDis + currNode.edges[i].weight) {
                distances[x].currDis = distances[shortestIndex].currDis + currNode.edges[i].weight;
                for (let k = 0; k < distances[shortestIndex].edges.length; k++) {
                    distances[x].edges.push(distances[shortestIndex].edges[k])
                    distances[x].nodes.push(distances[shortestIndex].nodes[k])
                }
                distances[x].edges.push(currNode.edges[i])
                distances[x].nodes.push(currNode)
                let e = document.getElementById('edge-' + (currNode.edges[i].id).toString())
                let otherNode = document.getElementById('node-' + (nodes[x].id).toString())
                elements.push(e)
                elements.push(otherNode)
            }
        }
        selectedElements.push(elements)
        elements = []
        visited[shortestIndex] = true;
    }
    let n = 0
    let interval1Time = 750
    let intervalID = setInterval(() => {
        if (n >= selectedElements.length) {
            if (selectedElements[n - 1] != null && selectedElements[n - 1].length > 0) {
                selectedElements[n - 1].forEach((e) => {
                    if (e.classList.contains('selected-element')) {
                        e.classList.remove('selected-element')
                    }
                })
            }
            clearInterval(intervalID)
            setTimeout(showPath, 500, distances, endIndex, setAlgorithmInProgress)
            return
        }
        selectedElements[n].forEach((e) => {
            e.classList.add('selected-element')
        })
        if (n > 0) {
            for (let a = 0; a < selectedElements[n - 1].length; a++) {
                if (!(selectedElements[n].some((e) => e.id === selectedElements[n - 1][a].id)) && 
                    selectedElements[n - 1][a].classList.contains('selected-element')) {
                    selectedElements[n - 1][a].classList.remove('selected-element')
                }
            }
        }
        n++
    }, interval1Time)
    return distances
}
