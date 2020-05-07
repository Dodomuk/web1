const songs = [
    "FlowerInBlue.mp3",
    "Nautilus.mp3",
    "QueensGarden.mp3",
    "TheFairyForest.mp3",
    "Title.mp3",
    "WhenTheMorningComes.mp3"
]

const createSongList = () => {
    const list = document.createElement('ol')

    for(let i = 0; i <songs.length; i++) {
        const item = document.createElement('li') //document.createElemnet: 지정된 html 요소를 생성
        item.appendChild(document.createTextNode(songs[i]))
        list.appendChild(item)
    }
return list
}

document.getElementById('songList').appendChild(createSongList())

songList.onclick = (e) => {
   //console.log(e)
   const clickedItem = e.target
   const source = document.getElementById('source')
   source.src = 'bgms/' + clickedItem.innerText

   document.getElementById('currentlyPlayingSong').innerText = "현재 재생곡:  "
   document.getElementById('currentSong').innerText = clickedItem.innerText

   player.load()
   player.play()
}

const playAudio = () => {
  if(player.readyState) {
      player.play()     
  }
}

const pauseAudio = () => {
    player.pause()
}

const slider = document.getElementById('volumeSlider')
slider.oninput = (e) => {
    const volume = e.target.value
    player.volume = volume
}

const updateProgress = () => {
    const progressBar = document.getElementById('progress')
    console.log(progressBar.value + "progress bar value")
    console.log(player.currentTime + "current time")
    console.log(player.duration + "duration")
    progressBar.value = (player.currentTime / player.duration) * 100
}