precision mediump float;
uniform vec3 spriteColor;
uniform sampler2D uTexture;
varying vec2 vTextureCoord;

void main() {
    gl_FragColor = vec4(spriteColor, 1.0) * texture2D(uTexture, vTextureCoord);
}