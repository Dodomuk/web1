let scene, camera, renderer, cube;

function init() {
    scene = new THREE.Scene();

    camera = new THREE.PerspectiveCamera(75,
        window.innerWidth / window.innerHeight,
        0.1,
        1000);

     renderer = new THREE.WebGLRenderer({ antialias: true }); //more edge looking

    renderer.setSize(window.innerWidth, window.innerHeight);

    document.body.appendChild(renderer.domElement);

    const geometry = new THREE.SphereGeometry( 0.5, 32, 32 );
    const texture = new THREE.TextureLoader().load('resources/texture/earth.png');
    const material = new THREE.MeshBasicMaterial({ map:texture });
    cube = new THREE.Mesh(geometry, material);
    scene.add(cube);

    camera.position.z = 3;
}
function animate() {
    requestAnimationFrame(animate);

    cube.rotation.x += 0.02;
    cube.rotation.y += 0.02;

    renderer.render(scene, camera);
}

function onWindowResize() {
    camera.aspect = window.innerWidth/window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);

}

window.addEventListener('resize', onWindowResize);
init();
animate();