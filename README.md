Hrecog
======

Project containing the Handwriting Recognition in Bengali Character


 Adaptive Stroke-based recognition of individual bengali characters.

 Features:
 - touchscreen based stroke input
 - Recognition of individual strokes of a character (one or more) by matching using DTW distance to a stored library of templates
 - Character recognition by a Look-up table of <stroke class name combinations, character class name>
 - Adapts from character-level user corrections by adding user's stroke to library as a new template
 
TODO:
- Implement LRU strategy for replacement of adapted samples to prevent bloated size of libraries
- Merge the various utility and file-building components
- User Manual
- Developer Manual
- Experimental results on users
- Compare adding new stroke samples to adaptation of existing samples via a LVQ (learning vector quantization) as done by Vuori et al.
- Test multi-modal inputs: levels of accuracy on pen-based strokes and finger-drawn strokes
