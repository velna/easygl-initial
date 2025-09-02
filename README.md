# easy-gl

这是一个面向对象的OpenGL API库，基于lwjgl。

## 本项目的目标：
1. 使用面向对象的方法来编写所有API，完全隔离lwjgl。
2. OpenGL中的所有常量全部使用枚举或接口实现，尽一切可能避免误使用。
3. 简化各类API的使用，减少代码量的同时尽可能保证性能。
4. 在调试模式下检查所有API调用，给出详尽的错误信息。（尚未实现）

## 进展
(根据[opengl45-quick-reference-card](https://www.khronos.org/files/opengl45-quick-reference-card.pdf)里的分类)

(部分OpengGL 4.5的API尚未实现）

| 类别  | 完成度 |
| ------------- | ------------- |
| Synchronization  | 100%  |
| Asynchronous Queries  | 100%  |
| Timer Queries  | 100%  |
| Buffer Objects  | 90%，除了一些查询方法  |
| Shaders and Programs  | 95%  |
| Textures and Samplers  | 20%  |
| Framebuffer Objects  | 90%，Texture相关只实现了2D  |
| Vertices  | 100%  |
| Vertex Arrays  | 20%  |
| Vertex Attributes  |  0% |
| Vertex Post-Processing  | 0%  |
| Rasterization  | 100%  |
| Fragment Shaders  | 0%  |
| Compute Shaders  | 0%  |
| Per-Fragment Operations  | 100%  |
| Hints  | 0%  |
| Whole Framebuffer  | 100%  |
| Reading and Copying Pixels  | 100%  |
| Debug Output  | 100%  |
| State and State Requests  | 不直接实现，全部分散到相关接口方法中  |

## 关于子模块learnopengl
所有代码移植自 [LearnOpenGL](https://github.com/JoeyDeVries/LearnOpenGL)，resources目录下的所有文件也全部来源于LearnOpenGL，特此说明。
