#ifdef GL_ES
precision mediump float;
#endif

attribute vec3 a_position;
attribute vec3 a_normal;

uniform mat4 u_modelMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_projectionMatrix;

uniform vec4 u_eyePosition;

//uniform vec4 u_color;
uniform vec4 u_lightDiffuse;
uniform vec4 u_lightPosition;

uniform vec4 u_lightPosition1;
uniform vec4 u_lightPosition3;


uniform float u_materialShininess;

uniform vec4 u_materialDiffuse;

varying vec4 v_color;

void main()
{
	vec4 position = vec4(a_position.x, a_position.y, a_position.z, 1.0);
	position = u_modelMatrix * position;

	vec4 normal = vec4(a_normal.x, a_normal.y, a_normal.z, 0.0);
	normal = u_modelMatrix * normal;

	//global coordinates

	//lighting

    vec4 v = u_eyePosition - position; //direction to the camera

    //light1
    vec4 s1 = u_lightPosition1;
    //vec4 h1 = s1+v;
    vec4 h1 = s1;
    float lambert1 = dot(normal,s1) / (length(normal) * length(s1));
    float phong1 = dot(normal,h1) /(length(normal) * length(h1));
    vec4 color1 = lambert1 * u_lightDiffuse * u_materialDiffuse + pow(phong1,u_materialShininess) * u_lightDiffuse * vec4(1,1,1,1);


    //light2

    vec4 s2 = u_lightPosition - position; //direction to the light
    vec4 h2 = s2 + v;
    //with what intensity does the direction of the light affect the surface
    float lambert2 = dot(normal, s2) / (length(normal) * length(s2));
    float phong2 = dot(normal, h2) / (length(normal) * length(h2));
    vec4 color2 = lambert2 * u_lightDiffuse * u_materialDiffuse + pow(phong2, u_materialShininess) * u_lightDiffuse * vec4(1,1,1,1);

    //light3
    vec4 s3 = u_lightPosition3; //direction to the light
    //vec4 h3 = s3 + v;
    vec4 h3 = s3;
    //with what intensity does the direction of the light affect the surface
    float lambert3 = dot(normal, s3) / (length(normal) * length(s3));
    float phong3 = dot(normal, h3) / (length(normal) * length(h3));
    vec4 color3 = lambert3 * u_lightDiffuse * u_materialDiffuse + pow(phong3, u_materialShininess) * u_lightDiffuse * vec4(1,1,1,1);

    //v_color = color1 + color2 + color3;
    v_color = color2;

	position = u_viewMatrix * position;

	//eye coordinates

	gl_Position = u_projectionMatrix * position;
}

//multiple light sources
//vec4 color1 = lambert1 * u_lightdiffuse1 * u_materialDiffuse;
//vec4 color2 ...
//v_color = color1 + color2 + ...