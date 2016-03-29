/**
 * Created by kuulart on 16.15.3.
 */
/*global define*/
define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    'use strict';

    var Equipment = Backbone.Model.extend({
        urlRoot : '/equipment'
    });

    return Equipment;
});
