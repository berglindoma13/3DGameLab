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

//test
uniform vec4 u_lightPosition1;
//end of test

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

    vec4 s = u_lightPosition - position; //direction to the light
    vec4 v = u_eyePosition - position; //direction to the camera]

    //test
    vec4 s1 = u_lightPosition1 - position;
    vec4 h1 = s1+v;
    float lambert1 = dot(normal,s1) / (length(normal) * length(s1));
    float phong1 = dot(normal,h1) /(length(normal) * length(h1));
    vec4 color1 = lambert1 * u_lightDiffuse * u_materialDiffuse + pow(phong1,u_materialShininess) * u_lightDiffuse * vec4(1,1,1,1);
    //end of test

    vec4 h = s + v;
    //with what intensity does the direction of the light affect the surface
    float lambert = dot(normal, s) / (length(normal) * length(s));
    float phong = dot(normal, h) / (length(normal) * length(h));
    vec4 color2 = lambert * u_lightDiffuse * u_materialDiffuse + pow(phong, u_materialShininess) * u_lightDiffuse * vec4(1,1,1,1);
    //v_color = lambert * u_lightDiffuse * u_materialDiffuse + pow(phong, u_materialShininess) * u_lightDiffuse * vec4(1,1,1,1);

    v_color = color1 + color2;

	position = u_viewMatrix * position;
	//normal = u_viewMatrix * normal;

	//eye coordinates

	//v_color = (dot(normal, normalize(vec4(-position.x,-position.y,-position.z,0))) / length(normal)) * u_color;

	gl_Position = u_projectionMatrix * position;
}

//multiple light sources
//vec4 color1 = lambert1 * u_lightdiffuse1 * u_materialDiffuse;
//vec4 color2 ...
//v_color = color1 + color2 + ...